

# Project Link: https://github.com/Sainathhiwale/advancedandroidbootcamp_2024/tree/DataBinding
# activitymain.xml file

<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

<androidx.constraintlayout.widget.ConstraintLayout
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:textSize="20sp"
        android:textAlignment="center"
        android:textColor="@color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="24dp"
        android:layout_marginEnd="8dp"
        android:labelFor="@id/editText"
        android:inputType="text"
        android:hint="@string/enter_text"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView" />

    <com.google.android.material.button.MaterialButton
        android:id="@+id/button_submit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/submit"
        android:layout_marginStart="10dp"
        android:layout_marginTop="24dp"
        android:layout_marginEnd="10dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/editText"/>

</androidx.constraintlayout.widget.ConstraintLayout>
</layout>

# MainActivity.kt File

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.examen.advancedandroidbootcamp_2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBinding.buttonSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_submit ->{
                getTextDisplay()
            }
        }
    }

    private fun getTextDisplay() {
        val text = mainBinding.editText.text.toString()
        mainBinding.textView.text = text
    }
}

# BuildFeatures(new update)

Starting from Android Gradle Plugin 4.0.0-alpha05 there is a new block called buildFeatures to enable build features.

So, instead of

android {
dataBinding{
    enabled = true
}
}
We need to enable data binding like this, inside the buildFeatures block.

android {
    buildFeatures{
         dataBinding = true
    }
}

# libs.versions.toml

[versions]
agp = "8.4.1"
kotlin = "1.9.0"
kotlin-kapt = "1.9.0"
coreKtx = "1.13.1"
junit = "4.13.2"
junitVersion = "1.2.1"
espressoCore = "3.6.1"
appcompat = "1.7.0"
material = "1.12.0"
activity = "1.9.1"
constraintlayout = "2.1.4"
databinding-runtime = "2.3.1"


[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-appcompat = { group = "androidx.appcompat", name = "appcompat", version.ref = "appcompat" }
material = { group = "com.google.android.material", name = "material", version.ref = "material" }
androidx-activity = { group = "androidx.activity", name = "activity", version.ref = "activity" }
androidx-constraintlayout = { group = "androidx.constraintlayout", name = "constraintlayout", version.ref = "constraintlayout" }
androidx-databinding-runtime = { group = "androidx.databinding", name = "databinding-runtime", version.ref = "databinding-runtime" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
jetbrains-kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }

# Project Level build Gradle file
plugins {
alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}
# App Level Build Gradle File - How to add databinding libs into build.gradle file
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id 'kotlin-kapt'
    
}

android {
    namespace 'com.examen.advancedandroidbootcamp_2024'
    compileSdk 34

    defaultConfig {
        applicationId "com.examen.advancedandroidbootcamp_2024"
        minSdk 24
        targetSdk 34
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    dataBinding {
        enabled true
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation libs.androidx.core.ktx
    implementation libs.androidx.appcompat
    implementation libs.material
    implementation libs.androidx.activity
    implementation libs.androidx.constraintlayout
    testImplementation libs.junit
    androidTestImplementation libs.androidx.junit
    androidTestImplementation libs.androidx.espresso.core
    implementation libs.androidx.databinding.runtime

}

# How to pass an object(data) to the xml layout and use them directly.In xml layout Define a variable for the object.
# MainActivity.kt
 val user = getUserData()
 mainBinding.user = getUserData()
  // binding object data to xml file directly
    fun getUserData():User{
        return User("Sainath",23,"Pune","Sainath Hiwale")
    }
# User Data class- Model Package
data class User(val name:String, val age:Int, val address:String, val empName:String)

# Set user object data to xml textview
# The <data> tag in the layout file is used to declare variables that can be used in the layout. In this case, it's declaring a variable named user of type com.examen.advancedandroidbootcamp_2024.model.User.
# This is a part of Android's Data Binding mechanism, which allows you to bind data from your kotlin code to your layout files.
Here's how it works:
You declare a variable in your layout file using the <data> tag.
You set the value of this variable in your Java code using the binding object.
You can then use this variable in your layout file to display data.
For example, let's say you have a User class with properties name and age

<data>
    <variable
        name="user"
        type="com.examen.advancedandroidbootcamp_2024.model.User" />
</data>

# To set user object to textview with help of  android:text="@{user.name}"  and  android:text="@{String.valueOf(user.age)}" if value is integer you need to convert into String.

<TextView
        android:id="@+id/textView_Name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:textSize="20sp"
        android:text="@{user.name}"
        android:textAlignment="center"
        android:textColor="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button_submit" />

    <TextView
        android:id="@+id/textView_Age"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:textSize="20sp"
        android:text="@{String.valueOf(user.age)}"
        android:textAlignment="center"
        android:textColor="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView_Name" />


# Example
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable
            name="user"
            type="com.examen.advancedandroidbootcamp_2024.model.User" />
    </data>
<androidx.constraintlayout.widget.ConstraintLayout
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:layout_marginTop="30dp"
        android:textSize="20sp"
        android:textAlignment="center"
        android:textColor="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="24dp"
        android:layout_marginEnd="8dp"
        android:labelFor="@id/editText"
        android:inputType="text"
        android:hint="@string/enter_text"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView" />

    <com.google.android.material.button.MaterialButton
        android:id="@+id/button_submit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/submit"
        android:layout_marginStart="10dp"
        android:layout_marginTop="24dp"
        android:layout_marginEnd="10dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/editText"/>
    <TextView
        android:id="@+id/textView_Name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:textSize="20sp"
        android:text="@{user.name}"
        android:textAlignment="center"
        android:textColor="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button_submit" />

    <TextView
        android:id="@+id/textView_Age"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:textSize="20sp"
        android:text="@{String.valueOf(user.age)}"
        android:textAlignment="center"
        android:textColor="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView_Name" />
</androidx.constraintlayout.widget.ConstraintLayout>
</layout>
