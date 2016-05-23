package com.speedment.runtime.internal.util.json;

import com.speedment.runtime.exception.SpeedmentException;
import static com.speedment.runtime.util.StaticClassUtil.instanceNotAllowed;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * A utility class that can parse strings into ordinary java objects.
 * <p>
 * JSON is parsed using the {@code RFC 7159} specification.
 * 
 * @author Emil Forslund
 * @since  2.4.0
 */
public final class Json {
    
    public static boolean PRETTY = true;
    
    /**
     * Parses the specified object to a JSON string. The following
     * objects are supported:
     * <ul>
     *      <li>{@code Map<String, Object>}
     *      <li>{@code List<Object>}
     *      <li>{@code String}
     *      <li>{@code Double}
     *      <li>{@code Long}
     *      <li>{@code null}
     * </ul>
     * <p>
     * This method will output a nicely formatted JSON string (with spaces and
     * new-lines).
     * 
     * @param object  the object to parse
     * @return        the parsed string
     * 
     * @throws IllegalArgumentException  if the inputed object is of or contains
     *                                   unsupported types
     */
    public static String toJson(Object object) throws IllegalArgumentException {
        try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            toJson(object, out);
            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        } catch (final IOException ex) {
            throw new SpeedmentException(
                "Error in internal toString()-stream.", ex
            );
        }
    }
    
    /**
     * Parses the specified object to JSON and prints it to the specified 
     * stream. The following objects are supported:
     * <ul>
     *      <li>{@code Map<String, Object>}
     *      <li>{@code List<Object>}
     *      <li>{@code String}
     *      <li>{@code Double}
     *      <li>{@code Long}
     *      <li>{@code null}
     * </ul>
     * <p>
     * This method will output a nicely formatted JSON string (with spaces and
     * new-lines).
     * 
     * @param object  the object to parse
     * @param out     the stream to write the result to
     * 
     * @throws IllegalArgumentException  if the inputed object is of or contains
     *                                   unsupported types
     * @throws IOException               if the stream could not be written to
     */
    public static void toJson(Object object, OutputStream out) throws IllegalArgumentException, IOException {
        new JsonSerializer(out, PRETTY).print(object);
    }
    
    /**
     * Parses the specified JSON string into a java object. The following
     * objects are supported:
     * <ul>
     *      <li>{@code Map<String, Object>}
     *      <li>{@code List<Object>}
     *      <li>{@code String}
     *      <li>{@code Double}
     *      <li>{@code Long}
     *      <li>{@code null}
     * </ul>
     * 
     * @param json  the json to parse
     * @return      the created object
     * 
     * @throws JsonSyntaxException  if the specified json is malformed  
     */
    public static Object fromJson(String json) throws JsonSyntaxException {
        try (final InputStream stream = 
            new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))) {
            
            return fromJson(stream);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Parses the specified JSON stream of unicode characters into a java 
     * object. The following objects are supported:
     * <ul>
     *      <li>{@code Map<String, Object>}
     *      <li>{@code List<Object>}
     *      <li>{@code String}
     *      <li>{@code Double}
     *      <li>{@code Long}
     *      <li>{@code null}
     * </ul>
     * 
     * @param in  the json to parse
     * @return    the created object
     * 
     * @throws IOException          if the stream can not be read 
     * @throws JsonSyntaxException  if the specified json is malformed  
     */
    public static Object fromJson(InputStream in) throws IOException, JsonSyntaxException {
        try (final JsonDeserializer parser = new JsonDeserializer(in)) {
            return parser.get();
        }
    }

    /**
     * Utility classes should never be instantiated.
     */
    private Json() {
        instanceNotAllowed(getClass());
    }
}