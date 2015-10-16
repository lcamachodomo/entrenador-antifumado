    package com.lecz.clubdelosvencedores.general;


    import android.app.Fragment;
    import android.app.FragmentTransaction;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.webkit.WebChromeClient;
    import android.webkit.WebView;
    import android.webkit.WebViewClient;
    import android.widget.LinearLayout;

    import com.lecz.clubdelosvencedores.R;

    import java.io.ByteArrayOutputStream;
    import java.io.IOException;
    import java.io.InputStream;

    /**
     *
     */
    public class HomeOne extends Fragment {
        // ...

        LinearLayout linlaHeaderProgress;
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_home_one, container, false);

            linlaHeaderProgress = (LinearLayout) rootView.findViewById(R.id.linlaHeaderProgress);
            linlaHeaderProgress.setVisibility(View.VISIBLE);
            WebView webview = (WebView)rootView.findViewById(R.id.goolge_map);

            webview.loadDataWithBaseURL(null, readTextFromResource(R.raw.goolgemaps), "text/html", "UTF-8", null);
            webview.setWebChromeClient(new WebChromeClient());
            webview.getSettings().setJavaScriptEnabled(true);

            webview.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    linlaHeaderProgress.setVisibility(View.GONE);
                }
            });

            return rootView;


        }

        private String readTextFromResource(int resourceID) {
            InputStream raw = getResources().openRawResource(resourceID);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            int i;
            try {
                i = raw.read();
                while (i != -1) {
                    stream.write(i);
                    i = raw.read();
                }
                raw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stream.toString();
        }
    }

