
package mypackage;
import com.stripe.Stripe;
/**
 *
 * @author songl
 */
public class StripeInitializer {
     public static void init() {
        // 替换为您自己的 Secret Key
        Stripe.apiKey = "sk_test_51RFYzgQTr6Rs5JlOQTj22sv3Nf5TFS8VsA9Xhnm9qxYt8Ugamv6zpEHRIL3ihGPhHQENi6ALDwZ3L0eQH2gBbRDP00IHXVyJr7";
    }
}
