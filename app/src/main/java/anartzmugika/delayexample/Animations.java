package anartzmugika.delayexample;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/******************************************************
 * Created by anartzmugika on 24/1/17.
 */

class Animations {
    private int lastPosition = -1;
    void setAnimationAppear(View view, int pos)
    {
        if (pos > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(/*new Random().nextInt(501)*/ 150);//to make duration random number between [0,501)
            view.startAnimation(anim);
            lastPosition = pos;
        }
    }
}
