    package com.example.myapplication3;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.graphics.Color;
    import android.os.Bundle;
    import android.text.Html;
    import android.text.Spannable;
    import android.text.SpannableString;
    import android.text.Spanned;
    import android.text.method.LinkMovementMethod;
    import android.text.method.ScrollingMovementMethod;
    import android.text.style.AbsoluteSizeSpan;
    import android.text.style.ForegroundColorSpan;
    import android.webkit.WebView;
    import android.widget.TextView;

    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.JsonObjectRequest;
    import com.android.volley.toolbox.Volley;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;


    public class DisplayMessageActivity extends AppCompatActivity {
        private TextView mTextVIewResult;
        private RequestQueue mQueqe;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_message);

            // Get the Intent that started this activity and extract the string
            Intent intent = getIntent();
            String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            mTextVIewResult = findViewById(R.id.text_view_result);
            mTextVIewResult.setClickable(true);
            mTextVIewResult.setMovementMethod(LinkMovementMethod.getInstance());
            //WebView myWebView = (WebView) findViewById(R.id.webView);

            //myWebView.loadUrl(test);
            mQueqe = Volley.newRequestQueue(this);
            jsonParse(message);
            mTextVIewResult.setMovementMethod(new ScrollingMovementMethod());
        }


        private void jsonParse(String message){
        String url = String.format("http://35.220.234.182:9200/new_google/_search?pretty&q=title:%1$s OR keyword:%1$s OR detail:%1$s", message);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONObject("hits").getJSONArray("hits");
                            for(int i = 0; i < jsonArray.length(); i++){
                                //Grab info out of JSON
                                JSONObject web = jsonArray.getJSONObject(i).getJSONObject("_source");

                                String title = web.getString("title");
                                String des = web.getString("description");
                                String url = web.getString("url");
                                //Settings for text sizes and color
                                //String text = "<a href='http://www.google.com'> Google </a>";
                                String link = "href='" + url + "'";
                                title = "<a " + link + ">" + title + "</a>";
                                title =  "<h4>" + title + "</h4>";

                                //Return view the word

                                mTextVIewResult.append(url + "\n");
                                mTextVIewResult.append(Html.fromHtml(title));
                                mTextVIewResult.append(des + "\n\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueqe.add(request);
    }

    }