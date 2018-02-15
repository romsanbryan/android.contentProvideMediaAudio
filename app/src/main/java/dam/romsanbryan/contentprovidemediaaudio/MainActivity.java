package dam.romsanbryan.contentprovidemediaaudio;



import android.os.Bundle;
import android.database.Cursor;
import android.app.Activity;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView texto;
    Uri contentUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI; // Archivos memoria interna
    //Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI; // Archivos memoria externa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = (TextView) findViewById(R.id.contactos);
        funcionBoton();
    }


    private void funcionBoton() {
        // TODO Auto-generated method stub
        String[] projection = new String[] {
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.AudioColumns.TITLE
        };

        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        // Get the index of the columns we need.
        int albumIdx = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM);
        int titleIdx = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE);
        // Create an array to store the result set.
        String[] result = new String[cursor.getCount()];
        /*
         * Iterate over the Cursor, extracting each album name and song
         * title.
         */
        while (cursor.moveToNext()) {
            // Extract the song title.
            String title = cursor.getString(titleIdx);
            // Extract the album name.
            String album = cursor.getString(albumIdx);
            result[cursor.getPosition()] = title + " (" + album + ")";
            texto.append(title + " (" + album + ")\n");
        }

        // Close the Cursor.
        cursor.close();
    }




}
