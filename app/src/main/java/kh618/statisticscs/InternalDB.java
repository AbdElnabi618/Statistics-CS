package kh618.statisticscs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class InternalDB extends SQLiteOpenHelper {

    private static final String name = "subj.db";
    private static final int version = 1;
    private SQLiteDatabase db;
    private Cursor cursor;


    public InternalDB(Context context) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table IF NOT EXISTS subject (id INTEGER primary key,subjectName TEXT," +
                "subjectType TEXT,subjectDate TEXT,downloadLink TEXT,num TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table IF EXISTS subject");
        onCreate(sqLiteDatabase);
    }

    //insert Details to db
    public void InsertRow(SubjectDetails details) {
        String subjectName = details.getSubName();
        String subjectType = details.getSubType();
        String subjectDate = details.getSubData();
        String downloadLink = details.getfile();
        String num = details.getNum();
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subjectName", subjectName);
        contentValues.put("subjectType", subjectType);
        contentValues.put("subjectDate", subjectDate);
        contentValues.put("downloadLink", downloadLink);
        contentValues.put("num",num);
        db.insert("subject", null, contentValues);
    }

    public boolean InsertArray(ArrayList<SubjectDetails> subjectDetailsArray) {
        boolean flag =false;
        for (int i = 0; i < subjectDetailsArray.size(); i++) {
            InsertRow(subjectDetailsArray.get(i));
            flag = true;
        }
        return flag;
    }

    //get  Details from db
    public ArrayList<SubjectDetails> getStatistics(String type) {
        return getInformation("Statistics", type);
    }

    public ArrayList<SubjectDetails> getMechanics(String type) {
        return getInformation("Mechanics", type);
    }
    public ArrayList<SubjectDetails> getMathematical_Biology(String type) {
        return getInformation("Mathematical-Biology", type);
    }

    public ArrayList<SubjectDetails> getLinear_Algebra(String type) {
        return getInformation("Linear-Algebra", type);
    }
    public ArrayList<SubjectDetails> getReal_Analysis(String type) {
        return getInformation("Real-Analysis", type);
    }

    public ArrayList<SubjectDetails> getGeometry(String type) {
        return getInformation("Geometry", type);
    }


    public ArrayList<SubjectDetails> getInformation(String subjectName, String subjectType) {
        ArrayList<SubjectDetails> arrayList = new ArrayList<SubjectDetails>();
        db = this.getReadableDatabase();
        cursor = db.rawQuery("select * from subject where subjectName='" + subjectName
                + "' AND subjectType='" + subjectType + "'", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            arrayList.add(get());
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<SubjectDetails> getAllInformation() {
        ArrayList arrayList = new ArrayList();
        db = this.getReadableDatabase();
        cursor = db.rawQuery("select * from subject", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            arrayList.add(get());
            cursor.moveToNext();
        }

        return arrayList;
    }

    public void delete() {
        db = this.getWritableDatabase();
        db.execSQL("delete  from subject");
    }

    private SubjectDetails get() {
        SubjectDetails details = new SubjectDetails();
        details.setfile(cursor.getString(cursor.getColumnIndex("downloadLink")));
        details.setSubData(cursor.getString(cursor.getColumnIndex("subjectDate")));
        details.setSubName(cursor.getString(cursor.getColumnIndex("subjectName")));
        details.setSubType(cursor.getString(cursor.getColumnIndex("subjectType")));
        details.setNum(cursor.getString(cursor.getColumnIndex("num")));
        return details;
    }
}
