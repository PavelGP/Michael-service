package by.of.servicebook.myapplication.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.activities.MainActivity;
import by.of.servicebook.myapplication.fragments.dialogs.BaseDialogFragment;
import by.of.servicebook.myapplication.fragments.dialogs.NoticeDialogFragment;
import by.of.servicebook.myapplication.parse.models.ParseService;
import by.of.servicebook.myapplication.utils.AppConst;
import by.of.servicebook.myapplication.utils.AppUtils;

/**
 * Created by Pavel on 21.04.2015.
 */
public class GarageFragment extends Fragment {
    private final int PICK_PHOTO_REQCODE = 100;
    private final int CAPTURE_IMAGE_REQCODE = 101;

    private ImageView ivDoc;
    private EditText etEmail;

    private ParseFile mDocFile;
    private String mClientEmail, mServiceId, mServiceName;

    private Uri fileUri;

    public static GarageFragment newInstance() {
        GarageFragment fragment = new GarageFragment();

        return fragment;
    }

    public GarageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(AppConst.FRAGMENT_GARAGE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_newgarage, container, false);

        ivDoc = (ImageView) v.findViewById(R.id.ivDoc);
        etEmail = (EditText) v.findViewById(R.id.etEmail);

        //pick image
        Button btnFile = (Button) v.findViewById(R.id.btnFile);
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        //capture image
        Button btnCamera = (Button) v.findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });

        Button btnSend = (Button) v.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendClientInfo();
            }
        });
        return v;
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_REQCODE);
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_REQCODE);
    }

    private void sendClientInfo(){
        //internet connection validation
        if (!AppUtils.isNetworkConnected(getActivity())){
            NoticeDialogFragment.newInstance(getString(R.string.warning), getString(R.string.no_internet), null)
                    .show(getFragmentManager(), null);
            return;
        }
        //email validation
        if (!AppUtils.isValidEmail(etEmail.getText().toString())){
            NoticeDialogFragment.newInstance(getString(R.string.warning), getString(R.string.not_valid_email), null)
                    .show(getFragmentManager(), null);
            return;
        }
        //file validation
        if (mDocFile == null){
            NoticeDialogFragment.newInstance(getString(R.string.warning), getString(R.string.no_file), null)
                    .show(getFragmentManager(), null);
            return;
        }

        mDocFile.saveInBackground();
        ParseService parseService = new ParseService("service_id", "service_name", etEmail.getText().toString(), mDocFile);
        parseService.saveInBackground();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mDocFile = null;
        if (requestCode == PICK_PHOTO_REQCODE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }

            //show image
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(
                    selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();


            Bitmap photo = BitmapFactory.decodeFile(filePath);
            ivDoc.setImageBitmap(photo);
            Log.d("TAG", "got result from galery");

            //create ParseFile object
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                int nRead;
                byte[] fileData = new byte[16384];
                while ((nRead = inputStream.read(fileData, 0, fileData.length)) != -1) {
                    buffer.write(fileData, 0, nRead);
                }
                buffer.flush();
                byte[] allFileData = buffer.toByteArray();

                mDocFile = new ParseFile(allFileData);

            } catch (IOException e){
                e.printStackTrace();
            }
        } else if (requestCode == CAPTURE_IMAGE_REQCODE && resultCode == Activity.RESULT_OK){
            if (data == null) {
                //Display an error
                return;
            }

            //show image
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivDoc.setImageBitmap(photo);
            Log.d("TAG", "got camera result");

            //create ParseFile object
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                int nRead;
                byte[] fileData = new byte[16384];
                while ((nRead = inputStream.read(fileData, 0, fileData.length)) != -1) {
                    buffer.write(fileData, 0, nRead);
                }
                buffer.flush();
                byte[] allFileData = buffer.toByteArray();

                mDocFile = new ParseFile(allFileData);

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
