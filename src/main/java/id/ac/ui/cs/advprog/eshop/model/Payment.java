package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id != null ? id : UUID.randomUUID().toString();
        this.method = method;
        this.paymentData = paymentData;
        this.status = validatePayment();
    }

    private String validatePayment() {
        if ("Voucher".equalsIgnoreCase(method)) {
            return validateVoucher();
        } else if ("CashOnDelivery".equalsIgnoreCase(method)) {
            return validateCashOnDelivery();
        }
        return "PENDING";
    }

    private String validateVoucher() {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP") && voucherCode.replaceAll("\\D", "").length() == 8) {
            return "SUCCESS";
        }
        return "REJECTED";
    }

    private String validateCashOnDelivery() {
        if (paymentData.get("address") == null || paymentData.get("address").isEmpty() ||
            paymentData.get("deliveryFee") == null || paymentData.get("deliveryFee").isEmpty()) {
            return "REJECTED";
        }
        return "PENDING";
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
