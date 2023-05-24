package com.example.coffeeshop.Services;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.example.coffeeshop.Activities.Login;
import com.example.coffeeshop.Contains.IpconfigLocalhost;
import com.example.coffeeshop.Contains.UserIsLoggedIn;
import com.example.coffeeshop.DTO.Cart;
import com.example.coffeeshop.DTO.Category;
import com.example.coffeeshop.DTO.DetailCart;
import com.example.coffeeshop.DTO.Product;
import com.example.coffeeshop.DTO.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class API {
    private final static String IP = "http://"+ IpconfigLocalhost.IPCONFIG +"/doanketthucmon/CoffeeShop/API.php";
    private final static String ROUTE = "?type=";
    private final static String GET_PRODUCT = "GetProducts";
    private final static String GET_CATEGORY = "GetCategories";
    private final static String ADD_PRODUCT = "AddProduct";
    private final static String UPDATE_PRODUCT = "UpdateProduct";


    private final static String GET_CART= "GetCart&id_user=";
    private final static String GET_CART_ID_CART= "GetCartByIdCart&id_cart=";

    private final static String GET_DETAILS_CART= "GetDetailsCart&id_user=";

    private final static String DETAILS_PRODUCT = "DetailsProduct&id=";

    private final static String ADD_TO_CART = "AddToCart&id_user=";

    private final static String PLACE_ORDER = "PlaceOrder&id_user=";

    private final static String DELETE_DETAIL_CART = "DeleteDetailCart&id=";

    private final static String UPDATE_DETAIL_CART = "UpdateDetailCart&id=";

    private final static String LIST_CART_ORDERED = "ListCartOrdered&id_user=";

    private final static String GET_DETAILS_CART_BY_ID_CART= "GetDetailsCartById&id_cart=";


    private final static String LOGIN = "Login&";
    private final static String REGISTER = "Register&";



    private static DetailCart ConvertJsonToDetailCart(JSONObject json)  throws JSONException
    {
        DetailCart detail = new DetailCart();
        detail.set_id(Integer.parseInt(json.getString("id").toString()) );
        detail.set_id_cart(Integer.parseInt(json.getString("id_cart").toString()) );
        detail.set_id_product(Integer.parseInt(json.getString("id_product").toString()) );
        detail.setQuantity(Integer.parseInt(json.getString("quantity").toString()) );
        detail.setPrice(Double.parseDouble(json.getString("price").toString()));

        return detail;

    }
    private static Product ConvertJsonToProduct(JSONObject json)  throws JSONException
    {
        Product product = new Product();
        product.set_id(Integer.parseInt(json.getString("id").toString()) );
        product.set_id_cate(Integer.parseInt(json.getString("id_cate").toString()));
        product.setPrice(Double.parseDouble(json.getString("price").toString()));
        product.setName(json.getString("name"));
        product.setDescription(json.getString("description"));
        product.setImage(json.getString("image"));
        product.setFavourite(false);

        return product;

    }
    private static Category ConvertJsonToCategory(JSONObject json)  throws JSONException
    {
        Category category = new Category();
        category.set_id(Integer.parseInt(json.getString("id").toString()) );
        category.setNameCategory(json.getString("name"));
        return category;

    }
    private static String ConvertUrlToJsonString(String strFrom) throws IOException {
        URL url = new URL(strFrom);
        URLConnection urlConn = url.openConnection();
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            builder.append(str);
        }

        String jsonStr = builder.toString();

        return jsonStr;
    }
    public static List<Product> GetProducts() throws IOException, JSONException {
        List<Product> products = new ArrayList<>();
        String url = IP+ROUTE+GET_PRODUCT;
        String data = ConvertUrlToJsonString(url);
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Product product = ConvertJsonToProduct(jsonObject);
            products.add(product);
        }
        return products;
    }
    public static List<Category> GetCategories() throws IOException, JSONException {
        List<Category> categories = new ArrayList<>();
        String url = IP+ROUTE+GET_CATEGORY;
        String data = ConvertUrlToJsonString(url);
        //String data = ConvertUrlToJsonString("https://cfshop.rf.gd/API.php");
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Category category = ConvertJsonToCategory(jsonObject);
            categories.add(category);
        }
        return categories;
    }
    public static Product GetProductById(int id) throws IOException, JSONException {
        String url = IP+ROUTE+DETAILS_PRODUCT+ Integer.toString(id);
        String data = ConvertUrlToJsonString(url);
        JSONObject json = new JSONObject(data);


        Product product = new Product();
        product.set_id(Integer.parseInt(json.getString("id").toString()) );
        product.set_id_cate(Integer.parseInt(json.getString("id_cate").toString()));
        product.setPrice(Double.parseDouble(json.getString("price").toString()));
        product.setName(json.getString("name"));
        product.setDescription(json.getString("description"));
        product.setImage(json.getString("image"));
        product.setFavourite(false);

        return product;

    }
    public static List<DetailCart> GetDetailsCartByUser(int id) throws IOException, JSONException {
        String url = IP+ROUTE+GET_DETAILS_CART+ Integer.toString(id);
        String data = ConvertUrlToJsonString(url);
        List<DetailCart> detailCartList = new ArrayList<>();
        if(data.equals("null") || data.isEmpty())
        {
            return null;
        }
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            DetailCart detail = ConvertJsonToDetailCart(jsonObject);
            detailCartList.add(detail);
        }
        return detailCartList;

    }
    public static List<DetailCart> GetDetailsCartByIdCart(int id_cart) throws IOException, JSONException {
        String url = IP+ROUTE+GET_DETAILS_CART_BY_ID_CART+ Integer.toString(id_cart);
        String data = ConvertUrlToJsonString(url);
        List<DetailCart> detailCartList = new ArrayList<>();
        if(data.equals("null") || data.isEmpty())
        {
            return null;
        }
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            DetailCart detail = ConvertJsonToDetailCart(jsonObject);
            detailCartList.add(detail);
        }
        return detailCartList;

    }
    public static Cart GetCartByIdUser(int id) throws IOException, JSONException {
        String url = IP+ROUTE+ GET_CART+  Integer.toString(id) ;
        String data = ConvertUrlToJsonString(url);

        if(data.equals("false"))
            return null;

        JSONObject json = new JSONObject(data);
        Cart cart = new Cart();
        cart.set_id(Integer.parseInt(json.getString("id").toString()) );
        cart.set_id_user(Integer.parseInt(json.getString("id_user").toString()) );
        cart.setStatus(Integer.parseInt(json.getString("_status").toString()) );
        cart.setTotal_price(Double.parseDouble(json.getString("total_price").toString()) );
        cart.setAddress(json.getString("address"));


        return cart;


    }
    public static User Login(String username, String password) throws IOException, JSONException {
        String user ="username="+ username+"&";
        String pass = "password="+ password;

        String url = IP+ROUTE+ LOGIN+user+pass ;
        String data = ConvertUrlToJsonString(url);
        if(data.equals("null"))
        {
            return null;
        }
        JSONObject json = new JSONObject(data);
        User _user = new User();
        _user.set_id(Integer.parseInt(json.getString("id").toString()) );
        _user.setFull_name(json.getString("full_name"));
        _user.set_username(json.getString("username"));
        _user.setPhone_number(json.getString("phonenumber"));
        _user.set_password(json.getString("_password"));
        _user.setRole(json.getString("role"));
        _user.setEmail(json.getString("email"));
        return _user;
    }

    public static void AddToCart(int id_user, int id_product) throws IOException {
        String url = IP+ROUTE+ ADD_TO_CART+  Integer.toString(id_user) +"&id_product="+Integer.toString(id_product)  ;
        String data = ConvertUrlToJsonString(url);

    }

    public static void DeleteDetailCart(int id,int id_user) throws IOException {
        String url = IP+ROUTE+ DELETE_DETAIL_CART+Integer.toString(id)  + "&id_user="+ Integer.toString(id_user);
        String data = ConvertUrlToJsonString(url);
    }

    public static void UpdateDetailCart(int id, int quantity, int id_user) throws IOException {
        String url = IP+ROUTE+ UPDATE_DETAIL_CART+Integer.toString(id)  + "&quantity="+ Integer.toString(quantity) +"&id_user="+id_user;
        String data = ConvertUrlToJsonString(url);

    }
    public static  void PlaceOrder(int id_user, String address) throws IOException {
        String url = IP+ROUTE+ PLACE_ORDER+Integer.toString(id_user) + "&address="+ address;
        String data = ConvertUrlToJsonString(url);
    }
    public static List<Cart> getAllCartsByUser_Ordered(int id_user) throws IOException, JSONException {
        String url = IP+ROUTE+ LIST_CART_ORDERED+  Integer.toString(id_user) ;
        String data = ConvertUrlToJsonString(url);
        if(data.equals(""))
            return null;
        List<Cart> carts = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = (JSONObject) jsonArray.get(i);
            Cart cart = new Cart();
            cart.set_id(Integer.parseInt(json.getString("id").toString()) );
            cart.set_id_user(Integer.parseInt(json.getString("id_user").toString()) );
            cart.setStatus(Integer.parseInt(json.getString("_status").toString()) );
            cart.setTotal_price(Double.parseDouble(json.getString("total_price").toString()) );
            cart.setAddress(json.getString("address"));
            carts.add(cart);
        }

        return carts;
    }

    public static Cart GetCartByIdCart(Integer id_cart) throws IOException, JSONException {
        String url = IP+ROUTE+ GET_CART_ID_CART+  Integer.toString(id_cart) ;
        String data = ConvertUrlToJsonString(url);

        if(data.equals("false"))
            return null;

        JSONObject json = new JSONObject(data);
        Cart cart = new Cart();
        cart.set_id(Integer.parseInt(json.getString("id").toString()) );
        cart.set_id_user(Integer.parseInt(json.getString("id_user").toString()) );
        cart.setStatus(Integer.parseInt(json.getString("_status").toString()) );
        cart.setTotal_price(Double.parseDouble(json.getString("total_price").toString()) );
        cart.setAddress(json.getString("address"));
        return cart;
    }
    public static void AddProduct(Product pro) throws IOException, JSONException {
        String url = IP+ROUTE+ ADD_PRODUCT+  "&name=" + pro.getName() +"&price="+pro.getPrice()+"&img="+pro.getImage()+"&desc="+pro.getDescription()+"&category="+pro.get_id_cate();
        String data = ConvertUrlToJsonString(url);
    }
    public static void UpdateProduct(Product pro) throws IOException, JSONException {
        String url = IP+ROUTE+ UPDATE_PRODUCT+"&id="+pro.get_id()+  "&name=" + pro.getName() +"&price="+pro.getPrice()+"&img="+pro.getImage()+"&desc="+pro.getDescription()+"&category="+pro.get_id_cate();
        String data = ConvertUrlToJsonString(url);
    }
    public static User Register(String username, String password,String email,String fullname,String phonenumber) throws IOException, JSONException {


        String url = IP+ROUTE+ REGISTER+"username="+username+"&password="+password+"&email="+email+"&fullname="+fullname+"&phonenumber="+phonenumber ;
        String data = ConvertUrlToJsonString(url);
        if(data.equals("null"))
        {
            return null;
        }
        JSONObject json = new JSONObject(data);
        User _user = new User();
        _user.set_id(Integer.parseInt(json.getString("id").toString()) );
        _user.setFull_name(json.getString("full_name"));
        _user.set_username(json.getString("username"));
        _user.setPhone_number(json.getString("phonenumber"));
        _user.set_password(json.getString("_password"));
        _user.setRole(json.getString("role"));
        _user.setEmail(json.getString("email"));
        return _user;
    }
}
