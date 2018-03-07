import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WineryConnector {

    public static HashMap<String, List<String>> getCategories()
    {
        HttpURLConnection connection = null;

        HashMap<String, List<String>> catValues = new HashMap<>();
        HashSet<String> categories = new HashSet<>();
        categories.add("grapes");
        categories.add("brand");
        categories.add("country");
        categories.add("taste");
        categories.add("colour");

        for (String category : categories) {
            try
            {
                URL url = new URL("http://localhost:8080/wines/" + category);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                System.out.println("Response code : " + responseCode);
                if (responseCode == 200)
                {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream())
                    );
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null)
                    {
                        response.append(inputLine);
                    }
                    in.close();

                    List<String> namesList = new ArrayList<>();

                    // JSON parsing here

                    JSONArray jsonarray = new JSONArray(response.toString());

                    for (int i = 0; i<jsonarray.length(); i++)
                    {
                        JSONObject jsonObject = jsonarray.getJSONObject(i);
                        String catName = jsonObject.getString(category);
                        System.out.println(catName);
                        namesList.add(catName);
                    }

                    catValues.put(category, namesList);
                    System.out.println(String.format("Values of %s added.\n", category));
                }
                else
                {
                    System.out.println(String.format("No URL for %s! Check your winery app!\n", category));
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return catValues;
    }

}
