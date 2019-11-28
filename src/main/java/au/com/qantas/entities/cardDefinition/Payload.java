package au.com.future-airlines.entities.cardDefinition;

/**
 * Created by boses on 26/06/2018.
 */
public class Payload {

    public NbaContent nbaContent;
    public NbaContentPostRego nbaContentPostRego;
    public NbaMetaAction nbaMetaAction;
    public NbaMetaOffer nbaMetaOffer;
    public NbaMetaProduct nbaMetaProduct;
    public NbaMetaTags nbaMetaTags;
    public Info info;

    public NbaContent getNbaContent() {
        return nbaContent;
    }

    public void setNbaContent(NbaContent nbaContent) {
        this.nbaContent = nbaContent;
    }

    public NbaContentPostRego getNbaContentPostRego() {
        return nbaContentPostRego;
    }

    public void setNbaContentPostRego(NbaContentPostRego nbaContentPostRego) {
        this.nbaContentPostRego = nbaContentPostRego;
    }

    public NbaMetaAction getNbaMetaAction() {
        return nbaMetaAction;
    }

    public void setNbaMetaAction(NbaMetaAction nbaMetaAction) {
        this.nbaMetaAction = nbaMetaAction;
    }

    public NbaMetaOffer getNbaMetaOffer() {
        return nbaMetaOffer;
    }

    public void setNbaMetaOffer(NbaMetaOffer nbaMetaOffer) {
        this.nbaMetaOffer = nbaMetaOffer;
    }

    public NbaMetaProduct getNbaMetaProduct() {
        return nbaMetaProduct;
    }

    public void setNbaMetaProduct(NbaMetaProduct nbaMetaProduct) {
        this.nbaMetaProduct = nbaMetaProduct;
    }

    public NbaMetaTags getNbaMetaTags() {
        return nbaMetaTags;
    }

    public void setNbaMetaTags(NbaMetaTags nbaMetaTags) {
        this.nbaMetaTags = nbaMetaTags;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
