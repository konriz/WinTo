package controller;

import Entities.Categories;
import Entities.Wine;
import exceptions.CategoryAddException;
import exceptions.WineAddException;
import exceptions.WineDeleteException;
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
            URL url = new URL("http://wineyard.herokuapp.com/wines/all");
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

                    Wine wine = new Wine(name, brand);

                    if(!jsonObject.isNull("colour"))
                    {
                        wine.setColour(jsonObject.getJSONObject("colour").getString("colour").toString());
                    }
                    else
                    {
                        wine.setColour("N/A");
                    }

                    String taste = "N/A";
                    if(!jsonObject.isNull("taste"))
                    {
                        wine.setTaste(jsonObject.getJSONObject("taste").getString("taste").toString());
                    }
                    else
                    {
                        wine.setTaste("N/A");
                    }

                    if(!jsonObject.isNull("drinked"))
                    {
                        wine.setDrinked(jsonObject.getBoolean("drinked"));
                    }

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


    public static List<String> getCategory(Categories type)
    {
        String category = type.toString();
        List<String> categories= new ArrayList<>();
        HttpURLConnection connection = null;

        try
        {

            URL url = new URL("http://wineyard.herokuapp.com/wines/" + category);
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
                    categories.add(catName);
                }

                Collections.sort(categories);
                System.out.println(String.format("Values of %s added.\n", category));
            }
            else
            {
                System.out.println(String.format("No URL for %s! Check your winery app!\n", category));
            }

        } catch (ConnectException e)
        {
            System.out.println("Connection failed, check your winery app!");
            categories.add("Pos1");
            categories.add("Pos2");
            categories.add("Pos3");
            System.out.println("Temporary items added\n");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return categories;
    }

    public static HashMap<String, List<String>> getCategories()
    {
        HashMap<String, List<String>> catValues = new HashMap<>();
        for (Categories cat : Categories.values()) {
            catValues.put(cat.toString(), getCategory(cat));
        }
        return catValues;
    }

    public static void addWine(Wine w) throws WineAddException
    {
        if (w.getName().length() < 1 || w.getBrand() == null)
        {
            throw new WineAddException();
        }
        else
        {
            HttpURLConnection connection = null;

            try{
                URL url = new URL("http://wineyard.herokuapp.com/wines");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                StringBuilder urlParameters = new StringBuilder();
                urlParameters.append("name=" + w.getName());
                urlParameters.append("&brand=" + w.getBrand());
                if (w.getGrapes() != null)
                {
                    urlParameters.append("&grapes=" + w.getGrapes());
                }
                if (w.getColour() != null)
                {
                    urlParameters.append("&colour=" + w.getColour());
                }
                if (w.getTaste() != null)
                {
                    urlParameters.append("&taste=" + w.getTaste());
                }
                if (w.getCountry() != null)
                {
                    urlParameters.append("&country=" + w.getCountry());
                }
                if (w.getYear() != null)
                {
                    urlParameters.append("&year=" + w.getYear());
                }
                if (w.getAlcohol() != null)
                {
                    urlParameters.append("&alcohol=" + w.getAlcohol());
                }
                if (w.getVolume() != null)
                {
                    urlParameters.append("&volume=" + w.getVolume());
                }
                if (w.getDrinked())
                {
                    urlParameters.append("&drinked=1");
                }
                else
                {
                    urlParameters.append("&drinked=0");
                }

                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(urlParameters.toString());
                wr.flush();
                wr.close();

                int responseCode = connection.getResponseCode();
                System.out.println("Response code : " + responseCode);

                if (responseCode == 200)
                {
                    System.out.println("Wine added : " + w.toString());

                }
                else
                {
                    throw new WineAddException();
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void deleteWine(Wine w) throws WineDeleteException
    {
        if (w.getName().length() < 1 || w.getBrand() == null)
        {
            throw new WineDeleteException();
        }
        else
        {
            HttpURLConnection connection = null;

            try {
                URL url = new URL("http://wineyard.herokuapp.com/wines/delete");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                StringBuilder urlParameters = new StringBuilder();
                urlParameters.append("name=" + w.getName());
                urlParameters.append("&brand=" + w.getBrand());

                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(urlParameters.toString());
                wr.flush();
                wr.close();

                int responseCode = connection.getResponseCode();

                System.out.println("Response code : " + responseCode);

                if (responseCode == 200) {
                    System.out.println("Wine deleted : " + w.toString());

                } else {
                    throw new WineDeleteException();
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void addItemToCategory(String item, Categories category) throws CategoryAddException
    {
        if (item.length()<1) {
            throw new CategoryAddException(category);
        }

        HttpURLConnection connection = null;
        try
        {
            URL url = new URL("http://wineyard.herokuapp.com/wines/" + category.toString());
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
