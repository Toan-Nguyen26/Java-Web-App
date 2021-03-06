    <application
    android:allowBackup=”true”
    android:icon=”@mipmap/ic_launcher”
    android:label=”@string/app_name”
    android:roundIcon=”@mipmap/ic_launcher_round”
    android:supportsRtl=”true”
    android:theme=”@style/AppTheme”>
    <activity
    android:name=”.MainScreenActivity”
    android:label=”@string/app_name”
    android:theme=”@style/AppTheme.NoActionBar”>
    <intent-filter>
    <action android:name=”android.intent.action.MAIN” />
    <category android:name=”android.intent.category.LAUNCHER” />
    </intent-filter>
    </activity>
    <! — All Product Activity →
    <activity
    android:name=”.AllProductsActivity”
    android:label=”All Products” >
    </activity>
    <! — Add Product Activity →
    <activity
    android:name=”.NewProductActivity”
    android:label=”Add New Product” >
    </activity>
    <! — Edit Product Activity →
    <activity
    android:name=”.EditProductActivity”
    android:label=”Edit Product” >
    </activity>
    </application>
    <! — Internet Permissions →
    <uses-permission android:name=”android.permission.INTERNET” />
    <uses-permission android:name=”android.permission.ACCESS_NETWORK_STATE” />