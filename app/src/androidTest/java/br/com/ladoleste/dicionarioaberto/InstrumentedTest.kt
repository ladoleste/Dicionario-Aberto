package br.com.ladoleste.dicionarioaberto

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.ladoleste.dicionarioaberto.app.OkHttpProvider
import br.com.ladoleste.dicionarioaberto.ui.MainActivity
import com.jakewharton.espresso.OkHttp3IdlingResource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {
    @Rule
    @JvmField
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRioDeJaneiro() {

        val idlingResource = OkHttp3IdlingResource.create("okhttp", OkHttpProvider.okHttpInstance)
        IdlingRegistry.getInstance().register(idlingResource)

        //Espresso.onView(withId(R.id.edit_text)).perform(typeText("Olá mundo!"))

        IdlingRegistry.getInstance().unregister(idlingResource)
    }

}
