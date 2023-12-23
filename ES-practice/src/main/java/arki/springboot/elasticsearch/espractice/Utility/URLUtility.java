package arki.springboot.elasticsearch.espractice.Utility;

import arki.springboot.elasticsearch.espractice.pojo.Job;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.client.RestHighLevelClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class URLUtility {

    static int failtureStringProcessingCount = 0;

    public HashMap<String, String> parseCookie(int i){
        String jobcards = "lang=v=2&lang=en-us; bcookie=\"v=2&ada8961c-cd68-47ed-83fe-dbe0e4a2c926\"; bscookie=\"v=1&20231127220442839844d6-0aa2-4943-884d-ea9d62d9d357AQGdvgUlLXPY2bOu8xGUgBR0b1Hg6T4B\"; _gcl_au=1.1.1260175932.1701122684; AMCVS_14215E3D5995C57C0A495C55%40AdobeOrg=1; aam_uuid=67210976583857115064571892298861564685; g_state={\"i_l\":0}; liap=true; li_at=AQEDAUFXbcQFmJaOAAABjBLQU78AAAGMNtzXv04AB2jewu5coUsnUe7ktLdsaoo9vm9uXhFsGcXRLS4hHxQAAa0ntIISKUTcpQUuCnS52xYOcw8wCnreKf0Xhq3N7BeZ4UnpSbwDg7tw3HVo2ZiKqIY-; JSESSIONID=\"ajax:6257000204951088526\"; timezone=America/St_Johns; li_theme=light; li_theme_set=app; _guid=395227cc-707d-4243-b515-37197965efa0; li_sugr=06533f80-e6e0-47a0-a111-d18404e11007; AnalyticsSyncHistory=AQL6TokQuqm2eAAAAYwS0FolQHRs_Ae_5TvRE-6PMK-XwPpim3mhBRMyLzI3sYMribvs7JjJRiqg36J7utGL8Q; lms_ads=AQEqUUGk5mN7JQAAAYwS0FrQOwQsKAXs7lctt9bo47C_FdubAXnR5fxaZYuEzOBcSbSaHif2YflxaOI8AzmfmajKAI9fzgi0; lms_analytics=AQEqUUGk5mN7JQAAAYwS0FrQOwQsKAXs7lctt9bo47C_FdubAXnR5fxaZYuEzOBcSbSaHif2YflxaOI8AzmfmajKAI9fzgi0; fid=AQGWVVAnRL8TZAAAAYwS0z1r23ZwROu3T5IcT70Q6bTmdP2IUOaKlvl7yQzhAwlOyILJPz0pNq4ZuQ; AMCV_14215E3D5995C57C0A495C55%40AdobeOrg=-637568504%7CMCIDTS%7C19689%7CMCMID%7C67399332010126669694593021731572949190%7CMCAAMLH-1701729940%7C7%7CMCAAMB-1701729940%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1701132340s%7CNONE%7CvVersion%7C5.1.1%7CMCCIDH%7C1517148558; UserMatchHistory=AQJZcJd6NGCETwAAAYwTZMsRoO8lZjULIdvGvJT-u1GHdwVnNMqsKrcUR7hrUDPLzTJv53JCwHiT7rOko6kK7cH-GYrBt9VPJdAB0FTNsAJi-j4ZKWKE_6VDRaeR7l1H-dXcWeCNVlOumJnD1F6ZYvh8RQWO1R5QR4G_5xJxzxquEzMpunK8LIaac-G5W_GXXktS7MjFfwdRM6FHpDBpSBN0s151QNCzdrS71FxWMfSf6ZlOrXcAwrK84n7D4kLVOmDhwDrUmxz32HFrfmik0pDmtBryJWqWPyw03_1XPd7WNdTlN-T2zOu-OzzJaBxi49f8d8c; lidc=\"b=TB72:s=T:r=T:a=T:p=T:g=3808:u=30:x=1:i=1701132418:t=1701205950:v=2:sig=AQGYB1JvFRdlCgEMRbLEEu9Jd_sovH5Y\"; __cf_bm=mjusj1fF_lQ7mvVdawBGuMqBkznSG8tq9Y6EAXOveto-1701132440-0-AVeXIj3AYeJp2w/8sPaylMkqwXK4RCOIRSoimDstEyZfWRvdkmU6IrpNenbYJQbNbwubnZ+jaH+LtQtkPIZ1cfU=; sdsc=22%3A1%2C1701132571119%7EJAPP%2C0SfLQxGeu6KkvMFFV69okQ35gjzA%3D";
        String specificdes = "lang=v=2&lang=en-us; bcookie=\"v=2&ada8961c-cd68-47ed-83fe-dbe0e4a2c926\"; bscookie=\"v=1&20231127220442839844d6-0aa2-4943-884d-ea9d62d9d357AQGdvgUlLXPY2bOu8xGUgBR0b1Hg6T4B\"; _gcl_au=1.1.1260175932.1701122684; AMCVS_14215E3D5995C57C0A495C55%40AdobeOrg=1; AMCV_14215E3D5995C57C0A495C55%40AdobeOrg=-637568504%7CMCIDTS%7C19689%7CMCMID%7C67399332010126669694593021731572949190%7CMCAAMLH-1701727483%7C7%7CMCAAMB-1701727483%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1701129883s%7CNONE%7CvVersion%7C5.1.1; aam_uuid=67210976583857115064571892298861564685; g_state={\"i_l\":0}; liap=true; li_at=AQEDAUFXbcQFmJaOAAABjBLQU78AAAGMNtzXv04AB2jewu5coUsnUe7ktLdsaoo9vm9uXhFsGcXRLS4hHxQAAa0ntIISKUTcpQUuCnS52xYOcw8wCnreKf0Xhq3N7BeZ4UnpSbwDg7tw3HVo2ZiKqIY-; JSESSIONID=\"ajax:6257000204951088526\"; timezone=America/St_Johns; li_theme=light; li_theme_set=app; _guid=395227cc-707d-4243-b515-37197965efa0; li_sugr=06533f80-e6e0-47a0-a111-d18404e11007; AnalyticsSyncHistory=AQL6TokQuqm2eAAAAYwS0FolQHRs_Ae_5TvRE-6PMK-XwPpim3mhBRMyLzI3sYMribvs7JjJRiqg36J7utGL8Q; lms_ads=AQEqUUGk5mN7JQAAAYwS0FrQOwQsKAXs7lctt9bo47C_FdubAXnR5fxaZYuEzOBcSbSaHif2YflxaOI8AzmfmajKAI9fzgi0; lms_analytics=AQEqUUGk5mN7JQAAAYwS0FrQOwQsKAXs7lctt9bo47C_FdubAXnR5fxaZYuEzOBcSbSaHif2YflxaOI8AzmfmajKAI9fzgi0; fid=AQGWVVAnRL8TZAAAAYwS0z1r23ZwROu3T5IcT70Q6bTmdP2IUOaKlvl7yQzhAwlOyILJPz0pNq4ZuQ; UserMatchHistory=AQKOsBU3BLpOcwAAAYwS7KMNpEg7AffsJoNTA1nFsCtMSqFgICbToM7Z11nZ3njJ7pz0mgVGiPBlPWhA28l1L4MDezSVCCvTdP88kRJ8wa08nC31fT_AM9XHvCZ8be5kz57kbaCq-6gRza6qZK9uc4iucn7eYm4fselcYQS8cD2xs6JV2uLT3p_5QQPKMvOtCxeTaCClYiL7834d57t7HzAL6awvw1v2LAveycvNIcOe9X-6oCVkw70Pcge0OOZDPcr1FXK4TrfYvfF7MgWLByi05DcuHWJTmPhfiPRrt0NifpBtrrQ9UKH9q4gfXXq8zfcvrbA; lidc=\"b=TB72:s=T:r=T:a=T:p=T:g=3808:u=30:x=1:i=1701124548:t=1701205950:v=2:sig=AQGskTEhtOCjwup1Cw5a0LjDmKul69li\"; sdsc=22%3A1%2C1701124548408%7EJAPP%2C0zWhe7wG6XQUcrdNiH1KsuWGf5aw%3D";
        String cookie = "";
        if (i == 0){
            cookie = jobcards;
        } else if (i == 1){
            cookie = specificdes;
        }
        String[] c = cookie.split("; ");
        HashMap<String, String> map = new HashMap<>();
        for (String c1 : c){
            String[] c2 = c1.split("=", 2);
            if (c2.length !=2) {
                continue;
            }
            map.put(c2[0], c2[1]);
        }
        return map;
    }

    public Document getDoc(){
        String url = "https://www.linkedin.com/jobs/collections/still-hiring/";
        String url2 = "https://www.linkedin.com/voyager/api/graphql?includeWebMetadata=true&variables=(count:25,jobCollectionSlug:still-hiring,query:(origin:GENERIC_JOB_COLLECTIONS_LANDING),start:25)&queryId=voyagerJobsDashJobCards.da56c4e71afbd3bcdb0a53b4ebd509c4";
        Document document;
        try {
            document = Jsoup.connect(url2)
//                .data("currentJobId", "3757466758").data("start", "0")
                .cookies(parseCookie(0))
                    .header("Csrf-Token", "ajax:6257000204951088526")
                    .header("accept", "application/json")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                .timeout(8000).ignoreContentType(true)
                .get();
            System.out.println(document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    public ArrayList<Job> monster(){
        ArrayList<Job> jobs = new ArrayList<>();
        for (int i = 7; i <= 9; i++){
            spider(jobs, 100, i*100);
        }
        return jobs;
    }

    public ArrayList<Job> spider(ArrayList<Job> jobs, int count, int start){
        String url2 = "https://www.linkedin.com/voyager/api/graphql?includeWebMetadata=true&variables=(count:" + count + ",jobCollectionSlug:still-hiring,query:(origin:GENERIC_JOB_COLLECTIONS_LANDING),start:" + start + ")&queryId=voyagerJobsDashJobCards.da56c4e71afbd3bcdb0a53b4ebd509c4";
        Document document;
        try {
            document = Jsoup.connect(url2)
                    .cookies(parseCookie(0))
                    .header("Csrf-Token", "ajax:6257000204951088526")
                    .header("accept", "application/json")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                    .timeout(8000).ignoreContentType(true)
                    .get();
            String data = document.body().text();
            JSONObject map = JSON.parseObject(data);
            JSONObject data2 = (JSONObject) map.get("data");
            JSONObject jobsDashJobCardsByJobCollections = (JSONObject) data2.get("jobsDashJobCardsByJobCollections");
            JSONArray elements = (JSONArray) jobsDashJobCardsByJobCollections.get("elements");
            System.out.println(elements.size());
            for (int i = 0; i < elements.size(); i++) {
                JSONObject almostthere = (JSONObject) elements.get(i);
                JSONObject jobCard = (JSONObject) almostthere.get("jobCard");
                JSONObject jobPostingCard = (JSONObject) jobCard.get("jobPostingCard");
                Job job = new Job();
                job.setName((String) jobPostingCard.get("jobPostingTitle"));
                JSONObject jobPosting = (JSONObject) jobPostingCard.get("jobPosting");
                String id = (String) jobPosting.get("trackingUrn");
                String[] idc = id.split(":");
                id = idc[idc.length-1];
                HashMap<String, String> map1 = spider2(id);
                job.setUrl(map1.get("companyurl"));
                job.setLogo(map1.get("logo"));
                job.setDescription(map1.get("jobdesc"));
                job.setId(id);
                JSONObject primaryDescription = (JSONObject) jobPostingCard.get("primaryDescription");
                job.setCompany((String) primaryDescription.get("text"));
                JSONObject secondaryDescription = (JSONObject) jobPostingCard.get("secondaryDescription");
                job.setLocation((String) secondaryDescription.get("text"));
                jobs.add(job);
                System.out.println(job.getName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jobs;
    }


    public HashMap spider2(String jobid){
        String url = "https://www.linkedin.com/jobs/collections/still-hiring/";
        Document document;
        HashMap<String, String> map = new HashMap<>();
        try {
            document = Jsoup.connect(url)
                .data("currentJobId", jobid)
                    .cookies(parseCookie(1))
                    .header("Csrf-Token", "ajax:6257000204951088526")
                    .header("accept", "application/json")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                    .timeout(8000).ignoreContentType(true)
                    .get();
            String jobdesc = subString(document.toString(), "$type\":\"com.linkedin.pemberly.text.Attribute\"}],\"text\":\"", "\",\"$type\":\"com.linkedin.pemberly.te");
            String logo = subString(document.toString(), "\"logo\":{\"image\":{\"$type\":\"com.linkedin.common.VectorImage\",\"rootUrl\":\"", "\",\"artifacts\":[{\"width\":200,\"expiresAt\":")
                    + subString(document.toString(), "{\"width\":100,\"expiresAt\":1709164800000,\"fileIdentifyingUrlPathSegment\":\"", "\",\"$type\":\"com.linkedin.common.VectorArtifact\",\"height\":100}");
            String companyurl = subString(document.toString(), "[\"com.linkedin.voyager.deco.organization.shared.WebCompactCompany\"],\"url\":\"", "\",\"$type\":\"com.linkedin.voyager.organization.Company\",");
            logo = logo.replaceAll("&amp;", "&");
            map.put("logo", logo);
            map.put("jobdesc", jobdesc);
            map.put("companyurl", companyurl);
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String subString(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            failtureStringProcessingCount++;
            return "字符串截取失败，未发现前数据！";
        }
        if (strEndIndex < 0) {
            failtureStringProcessingCount++;
            return "字符串截取失败，未发现后数据！";
        }

        int occurrence = StringUtils.countOccurrencesOf(str, strStart);
        int occurrence2 = StringUtils.countOccurrencesOf(str, strEnd);
        if (occurrence2 != 1 || occurrence != 1){
            failtureStringProcessingCount++;
            return "截取字符串多次出现！";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }

}
