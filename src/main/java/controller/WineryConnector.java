package controller;

import Entities.Categories;
import Entities.Wine;
import exceptions.CategoryAddException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

public class WineryConnector {


    public static List<Entities.Wine> getWines()
    {
        HttpURLConnection connection = null;

        List<Entities.Wine> wines = new ArrayList<>();

        try
        {
            URL url = new URL("http://localhost:8080/wines/all");
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

                JSONArray jsonarray = new JSONArray(response.toString());

                for (int i = 0; i<jsonarray.length(); i++)
                {
                    JSONObject jsonObject = jsonarray.getJSONObject(i);
                    String name = jsonObject.getString("name");

                    JSONObject brandJSON = jsonObject.getJSONObject("brand");
                    String brand = brandJSON.getString("brand").toString();

                    // TODO handle nulls!

//                    JSONObject colourJSON = jsonObject.getJSONObject("colour");
//                    String colour = colourJSON.getString("colour").toString();
//
//                    JSONObject tasteJSON = jsonObject.getJSONObject("taste");
//                    String taste = tasteJSON.getString("taste").toString();

                    Entities.Wine wine = new Wine(name, brand);
                    wines.add(wine);

                }

                System.out.println(String.format("%s wines added.", wines.size()));
            }
            else
            {
                System.out.println(String.format("Error! Check your winery app!\n"));
            }


        }
        catch (ConnectException e)
        {
            System.out.println("Connection failed, check your winery app!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return wines;

    }

    public static HashMap<String, List<String>> getCategories()
    {
        HttpURLConnection connection = null;

        HashMap<String, List<String>> catValues = new HashMap<>();

        for (Categories cat : Categories.values()) {

            String category = cat.toString();

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

                    JSONArray jsonarray = new JSONArray(response.toString());

                    for (int i = 0; i<jsonarray.length(); i++)
                    {
                        JSONObject jsonObject = jsonarray.getJSONObject(i);
                        String catName = jsonObject.getString(category);
                        System.out.println(catName);
                        namesList.add(catName);
                    }

                    Collections.sort(namesList);
                    catValues.put(category, namesList);
                    System.out.println(String.format("Values of %s added.\n", category));
                }
                else
                {
                    System.out.println(String.format("No URL for %s! Check your winery app!\n", category));
                }

            } catch (ConnectException e)
            {
                System.out.println("Connection failed, check your winery app!");
                // FIXME temporary fix
                List<String> namesList = new ArrayList<>();
                namesList.add("Pos1");
                namesList.add("Pos2");
                namesList.add("Pos3");
                catValues.put(category, namesList);
                System.out.println("Temporary items added\n");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return catValues;
    }

    // TODO add wine method!

    // Use this to add new country, brand...

    public static void addItemToCategory(String item, Categories category) throws CategoryAddException
    {
        if (item.length()<1) {
            throw new CategoryAddException(category);
        }

        HttpURLConnection connection = null;
        try
        {
            URL url = new URL("http://localhost:8080/wines/" + category.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            String urlParameters = category.toString()+ "=" + item;
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = connection.getResponseCode();
            System.out.println("Response code : " + responseCode);


        } catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(String.format("%s : %s added!", category.toString(), item));
    }

}
