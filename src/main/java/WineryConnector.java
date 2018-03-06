import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WineryConnector {

    static Gson g = new Gson();

    public static void getCategories()
    {
        HttpURLConnection connection = null;

        HashMap<String, List<String>> catValues = new HashMap<>();
        HashSet<String> categories = new HashSet<>();
        categories.add("grapes");
        categories.add("brands");
        categories.add("countries");
        categories.add("tastes");
        categories.add("colours");

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

                    catValues.put(category, getNames(category, response.toString()));
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

    }

    private static List<String> getNames(String category, String response)
    {
        List<String> resultSet = new ArrayList<>();
        switch (category)
        {
            case "grapes":
                Grapes[] grapes = g.fromJson(response, Grapes[].class);
                for (Grapes g :
                        grapes) {
                    resultSet.add(g.getName());
                }

                break;
            case "brands":

                break;
            case "countries":

                break;
            case "tastes":
                Taste[] tastes = g.fromJson(response, Taste[].class);
                for (Taste t :
                        tastes) {
                    resultSet.add(t.getTaste());
                }
                break;
            case "colours":

                break;
            default:
                break;
        }
        for(String s : resultSet)
        {
            System.out.println(s);
        }
        return resultSet;
    }

}
