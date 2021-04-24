# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepattributes *Annotation*,Signature
-keepclassmembers enum * { *; }
-dontwarn org.jetbrains.annotations.**
-keep class kotlin.Metadata { *; }
-keep class com.hb.pages.api.*.*,
    com.hb.pages.api.google.model.*,
    com.hb.pages.api.google.*,
    com.hb.pages.api.google.mapper.*,
    com.hb.pages.model.*,
    com.hb.pages.view.NavigationBar.*,
    com.hb.pages.view.NavigationBar.NavigationTab.*,
    com.hb.pages.features.books.BooksFragment,
    com.hb.pages.features.settings.SettingsFragment,
    com.hb.pages.features.statistics.StatisticsFragment,
    com.hb.pages.features.wishlist.WishlistFragment,
    org.threeten.bp.* {
    <fields>;
    <init>();
    <methods>;
}