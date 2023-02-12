package com.google.firebase.database.core.utilities;

import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.RepoInfo;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Map;

public class Utilities {
    private static final char[] HEX_CHARACTERS = "0123456789abcdef".toCharArray();

    public static ParsedUrl parseUrl(String url) throws DatabaseException {
        try {
            Uri uri = Uri.parse(url);
            String scheme = uri.getScheme();
            if (scheme != null) {
                String host = uri.getHost();
                if (host != null) {
                    String namespace = uri.getQueryParameter("ns");
                    boolean z = false;
                    if (namespace == null) {
                        namespace = host.split("\\.", -1)[0].toLowerCase(Locale.US);
                    }
                    RepoInfo repoInfo = new RepoInfo();
                    repoInfo.host = host.toLowerCase(Locale.US);
                    int port = uri.getPort();
                    if (port != -1) {
                        if (scheme.equals("https") || scheme.equals("wss")) {
                            z = true;
                        }
                        repoInfo.secure = z;
                        repoInfo.host += ":" + port;
                    } else {
                        repoInfo.secure = true;
                    }
                    repoInfo.internalHost = repoInfo.host;
                    repoInfo.namespace = namespace;
                    String originalPathString = extractPathString(url).replace("+", " ");
                    Validation.validateRootPathString(originalPathString);
                    ParsedUrl parsedUrl = new ParsedUrl();
                    parsedUrl.path = new Path(originalPathString);
                    parsedUrl.repoInfo = repoInfo;
                    return parsedUrl;
                }
                throw new IllegalArgumentException("Database URL does not specify a valid host");
            }
            throw new IllegalArgumentException("Database URL does not specify a URL scheme");
        } catch (Exception e) {
            throw new DatabaseException("Invalid Firebase Database url specified: " + url, e);
        }
    }

    private static String extractPathString(String originalUrl) {
        int schemeOffset = originalUrl.indexOf("//");
        if (schemeOffset != -1) {
            String urlWithoutScheme = originalUrl.substring(schemeOffset + 2);
            int pathOffset = urlWithoutScheme.indexOf("/");
            if (pathOffset == -1) {
                return "";
            }
            int queryOffset = urlWithoutScheme.indexOf("?");
            if (queryOffset != -1) {
                return urlWithoutScheme.substring(pathOffset + 1, queryOffset);
            }
            return urlWithoutScheme.substring(pathOffset + 1);
        }
        throw new DatabaseException("Firebase Database URL is missing URL scheme");
    }

    public static String sha1HexDigest(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(input.getBytes("UTF-8"));
            return Base64.encodeToString(md.digest(), 2);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Missing SHA-1 MessageDigest provider.", e);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("UTF-8 encoding is required for Firebase Database to run!");
        }
    }

    public static String stringHashV2Representation(String value) {
        String escaped = value;
        if (value.indexOf(92) != -1) {
            escaped = escaped.replace("\\", "\\\\");
        }
        if (value.indexOf(34) != -1) {
            escaped = escaped.replace("\"", "\\\"");
        }
        return '\"' + escaped + '\"';
    }

    public static String doubleToHashString(double value) {
        StringBuilder sb = new StringBuilder(16);
        long bits = Double.doubleToLongBits(value);
        for (int i = 7; i >= 0; i--) {
            int byteValue = (int) ((bits >>> (i * 8)) & 255);
            char[] cArr = HEX_CHARACTERS;
            sb.append(cArr[(byteValue >> 4) & 15]);
            sb.append(cArr[byteValue & 15]);
        }
        return sb.toString();
    }

    public static Integer tryParseInt(String num) {
        if (num.length() > 11 || num.length() == 0) {
            return null;
        }
        int i = 0;
        boolean negative = false;
        if (num.charAt(0) == '-') {
            if (num.length() == 1) {
                return null;
            }
            negative = true;
            i = 1;
        }
        long number = 0;
        while (i < num.length()) {
            char c = num.charAt(i);
            if (c < '0' || c > '9') {
                return null;
            }
            number = (10 * number) + ((long) (c - '0'));
            i++;
        }
        if (negative) {
            if ((-number) < -2147483648L) {
                return null;
            }
            return Integer.valueOf((int) (-number));
        } else if (number > 2147483647L) {
            return null;
        } else {
            return Integer.valueOf((int) number);
        }
    }

    public static int compareInts(int i, int j) {
        if (i < j) {
            return -1;
        }
        if (i == j) {
            return 0;
        }
        return 1;
    }

    public static int compareLongs(long i, long j) {
        if (i < j) {
            return -1;
        }
        if (i == j) {
            return 0;
        }
        return 1;
    }

    public static <C> C castOrNull(Object o, Class<C> clazz) {
        if (clazz.isAssignableFrom(o.getClass())) {
            return o;
        }
        return null;
    }

    public static <C> C getOrNull(Object o, String key, Class<C> clazz) {
        Object result;
        if (o == null || (result = ((Map) castOrNull(o, Map.class)).get(key)) == null) {
            return null;
        }
        return castOrNull(result, clazz);
    }

    public static void hardAssert(boolean condition) {
        hardAssert(condition, "");
    }

    public static void hardAssert(boolean condition, String message) {
        if (!condition) {
            Log.w("FirebaseDatabase", "Assertion failed: " + message);
        }
    }

    public static Pair<Task<Void>, DatabaseReference.CompletionListener> wrapOnComplete(DatabaseReference.CompletionListener optListener) {
        if (optListener != null) {
            return new Pair<>(null, optListener);
        }
        final TaskCompletionSource<Void> source = new TaskCompletionSource<>();
        return new Pair<>(source.getTask(), new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error != null) {
                    TaskCompletionSource.this.setException(error.toException());
                } else {
                    TaskCompletionSource.this.setResult(null);
                }
            }
        });
    }

    public static boolean equals(Object left, Object right) {
        if (left == right) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.equals(right);
    }
}
