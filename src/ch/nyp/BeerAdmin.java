package ch.nyp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class BeerAdmin {

	String url = "http://api.brewerydb.com/v2/";
	String apiKey = "key=1511d0db4a1d6841481c672455358cff";

	HashMap<Integer, String> beerStyles;
	HashMap<String, Beer> beerList;
	
	public static void main(String[] args) {
		BeerAdmin ba = new BeerAdmin();
		ba.loadBeerStyles();
		//ba.printBeerStyles();
		//ba.printBeerStyles("Pale");
		ba.getBeerListForStyle(5);
		ba.printBeerList();
	}

	public void loadBeerStyles() {
		String urlExtension = "styles/?";
		JSONObject object = getObject(url + urlExtension + apiKey);

		beerStyles = new HashMap<Integer, String>();

		for (int i = 0; i < object.getJSONArray("data").length(); i++) {
			int id = object.getJSONArray("data").getJSONObject(i).getInt("id");
			String name = object.getJSONArray("data").getJSONObject(i).getString("name");
			beerStyles.put(id, name);
		}
	}

	public String printBeerStyles() {
		StringBuilder text = new StringBuilder();
		for (Integer key : beerStyles.keySet()) {
			text.append(key + "::" + beerStyles.get(key) + "\n");
		}
		return text.toString();
	}

	public void printBeerStyles(String search) {
		for (Integer key : beerStyles.keySet()) {
			if (beerStyles.get(key).contains(search)) {
				System.out.println(key + "::" + beerStyles.get(key));
			}	
		}
	}

	public void getBeerListForStyle(int idStyle) {
		String urlExtension = "beers/?styleId=" + idStyle + "&";
		JSONObject object = getObject(url + urlExtension + apiKey);
		Beer beer;
		beerList = new HashMap<String, Beer>();
		
		for (int i = 0; i < object.getJSONArray("data").length(); i++) {
			String id = object.getJSONArray("data").getJSONObject(i).getString("id");
			String name = object.getJSONArray("data").getJSONObject(i).getString("name");
			String description = object.getJSONArray("data").getJSONObject(i).getJSONObject("style").getString("description");
			beer = new Beer(id, name, description, idStyle);
			beerList.put(beer.getId(), beer);
		}
	}
	
	public void printBeerList() {
		for (String key : beerList.keySet()) {
			System.out.println(key + "::" + beerList.get(key).getName());
		}
	}
	
	public void printBeerList(String id) {
		for (String key : beerList.keySet()) {
			if (key.equals(id)) {
				System.out.println(key + "::" + beerList.get(key).getName());
			}
		}
	}

	private JSONObject getObject(String link) {
		URL resource = null;
		URLConnection connection = null;

		try {
			resource = new URL(link);
			connection = resource.openConnection();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuilder builder = new StringBuilder();
		String line;

		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = read.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		JSONObject object = null;
		try {
			object = new JSONObject(builder.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return object;
	}
}
