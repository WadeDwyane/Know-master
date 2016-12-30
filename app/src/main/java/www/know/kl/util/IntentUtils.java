package www.retrofit.com.retrofitrxjavademo.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

/**
 * 跳转
 */
public class IntentUtils {

    public static Intent getIntent(String[] name, Object... content) {
        Intent intent = new Intent();
        if (null != name && null != content) {
            int i = name.length;
            while (i-- > 0) {
                putExtra(name[i], content[i], intent);
            }
        }

        return intent;
    }

    public static void startIntent(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startIntent(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public static Intent startIntent(Context context, Class clazz, String name,
                                     Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(name, bundle);
        context.startActivity(intent);

        return intent;
    }

    public static Intent startService(Context context, Class clazz) {
        Intent service = new Intent(context, clazz);
        context.startService(service);
        return service;
    }

    public static Intent startService(ContextWrapper context, Class clazz) {
        Intent service = new Intent(context, clazz);
        context.startService(service);
        return service;
    }

    public static void startService(Context context, Class clazz, String name,
                                    Serializable obj) {
        Intent service = new Intent(context, clazz);
        service.putExtra(name, obj);
        context.startService(service);
    }

    public static void stopService(Context context, Class clazz) {
        Intent service = new Intent(context, clazz);
        context.stopService(service);
    }

    public static void startIntent(Context context, Class clazz, String name,
                                   Object content) {
        Intent intent = new Intent(context, clazz);
        putExtra(name, content, intent);

        context.startActivity(intent);
    }

    public static void startIntent(Context context, Class clazz, String name,
                                   Serializable obj) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(name, obj);

        context.startActivity(intent);
    }

    public static void startIntent(Context context, Class clazz, String[] name,
                                   Object[] content) {
        Intent intent = new Intent(context, clazz);
        int i = name.length;
        while (i-- > 0) {
            putExtra(name[i], content[i], intent);
        }
        context.startActivity(intent);
    }

    public static void startIntent(Context context, Class clazz,
                                   String objName, Serializable obj, String name, Object content) {
        Intent intent = new Intent(context, clazz);
        putExtra(name, content, intent);
        intent.putExtra(objName, obj);
        context.startActivity(intent);
    }

    public static void startIntentForReuslt(Context context, Class clazz,
                                            String objName, Serializable obj, String name, Object content,
                                            int requestCode) {
        Intent intent = new Intent(context, clazz);
        putExtra(name, content, intent);
        intent.putExtra(objName, obj);
        startIntentForReuslt(context, intent, requestCode);
    }

    public static void startIntentForReuslt(Context context, Class clazz,
                                            String objName, Serializable obj, String[] name, Object[] content,
                                            int requestCode) {
        Intent intent = new Intent(context, clazz);
        int i = content.length;
        while (i-- > 0) {
            putExtra(name[i], content[i], intent);
        }
        intent.putExtra(objName, obj);
        startIntentForReuslt(context, intent, requestCode);
    }

    public static void startIntentForReuslt(Context context, Class clazz,
                                            String name, Object content, int requestCode) {
        Intent intent = new Intent(context, clazz);

        putExtra(name, content, intent);

        startIntentForReuslt(context, intent, requestCode);
    }

    public static void startIntentForReuslt(Context context, Class clazz,
                                            String[] name, Object[] content, int requestCode) {
        Intent intent = new Intent(context, clazz);

        int i = name.length;
        while (i-- > 0) {
            putExtra(name[i], content[i], intent);
        }

        startIntentForReuslt(context, intent, requestCode);
    }

    public static void startIntentForReuslt(Context context, Intent intent, int requestCode) {
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    private static void putExtra(String name, Object content, Intent intent) {
        if (content instanceof String) {
            intent.putExtra(name, (String) content);
        } else if (content instanceof Boolean) {
            intent.putExtra(name, (Boolean) content);

        } else if (content instanceof Integer) {

            intent.putExtra(name, (Integer) content);
        } else if (content instanceof Float) {

            intent.putExtra(name, (Float) content);
        } else if (content instanceof Serializable) {
            intent.putExtra(name, (Serializable) content);
        } else {

        }
    }

}
