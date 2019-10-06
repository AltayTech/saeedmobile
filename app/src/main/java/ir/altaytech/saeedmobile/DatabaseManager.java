package ir.altaytech.saeedmobile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ir.altaytech.saeedmobile.Model.Product;
import ir.altaytech.saeedmobile.Model.other.Categoryo;
import ir.altaytech.saeedmobile.Model.other.Product2;

/**
 * Created by Altay on 2/28/2018.
 */

public class DatabaseManager {

    private static ArrayList<Product> updatedProductList;

    private static SQLiteDatabase sqLiteDatabase;
    private static List<Product> TotalFavoriteProductList;


    public static void openDatabase(Context context) {

        updatedProductList = new ArrayList<>();
        TotalFavoriteProductList = new ArrayList<>();

        try {

            sqLiteDatabase = context.openOrCreateDatabase("MarketDatabase", Context.MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS favorite(mainId INT,title VANCHAR,shortdesc VANCHAR,price INT,image VANCHAR)");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Favorite List table

    public static void insertRowFavorite(Product product) {
//        int num = 1;
        int mainId = product.getId();
        String title = product.getShortName();
        String shortdesc = product.getDescription();
        int price = product.getPrice();
        String image = product.getPicPath();

        sqLiteDatabase.execSQL("INSERT INTO favorite(mainId ,title ,shortdesc ,price ,image ) VALUES('" + mainId + "','" + title + "','" + shortdesc  + "','" + price + "','" + image + "')");

    }

    public static List<Product> readRowFavorite(int id, Context context) {

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM favorite WHERE mainId = " + id + " ", null);

        int mainIdIndex = cursor.getColumnIndex("mainId");
        int titleIndex = cursor.getColumnIndex("title");
        int shortdescIndex = cursor.getColumnIndex("shortdesc");
        int priceIndex = cursor.getColumnIndex("price");
        int imageIndex = cursor.getColumnIndex("image");
        cursor.moveToFirst();

        try {
            while (cursor != null) {

                updatedProductList.add(new Product(
                        cursor.getInt(mainIdIndex),
                        cursor.getString(titleIndex),
                        cursor.getString(shortdescIndex),
                        cursor.getInt(priceIndex),
                        cursor.getString(imageIndex)
                ));

                cursor.moveToNext();
            }
        } catch (Exception e) {
            Log.e("database", e.getMessage());
        }


        return updatedProductList;

    }

    public static List<Product> readListFavorite() {

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM favorite ", null);
        int mainIdIndex = cursor.getColumnIndex("mainId");
        int titleIndex = cursor.getColumnIndex("title");
        int shortdescIndex = cursor.getColumnIndex("shortdesc");
        int priceIndex = cursor.getColumnIndex("price");
        int imageIndex = cursor.getColumnIndex("image");
        cursor.moveToFirst();

        try {
            while (cursor != null) {

                TotalFavoriteProductList.add(new Product(
                        cursor.getInt(mainIdIndex),
                        cursor.getString(titleIndex),
                        cursor.getString(shortdescIndex),
                        cursor.getInt(priceIndex),
                        cursor.getString(imageIndex)

                ));

                cursor.moveToNext();
            }
        } catch (Exception e) {
            Log.e("database", e.getMessage());
        }

        return TotalFavoriteProductList;
    }

    public static void deleteRowFavorite(int id) {

        sqLiteDatabase.execSQL("DELETE FROM favorite WHERE mainId = " + id + " ");

    }

    public static boolean productIsExistFavorite(int id, Context context) {
        boolean isExist;
        isExist = false;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM favorite", null);
        int mainIdIndex = cursor.getColumnIndex("mainId");

        cursor.moveToFirst();

        try {
            while (cursor != null) {

                if (id == cursor.getInt(mainIdIndex)) {
                    isExist = true;
                    break;
                }


                cursor.moveToNext();
            }
        } catch (Exception e) {
            Log.e("database", e.getMessage());
        }


        return isExist;
    }

}
