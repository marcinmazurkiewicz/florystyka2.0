package dev.mazurkiewicz.quizer.pdf;

import com.lowagie.text.Image;
import dev.mazurkiewicz.quizer.exception.PdfRenderException;
import dev.mazurkiewicz.quizer.resource.ResourceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageReplacedElementFactory implements ReplacedElementFactory {

    ReplacedElementFactory superFactory;
    ResourceService resourceService;

    @Override
    public ReplacedElement createReplacedElement(LayoutContext layoutContext,
                                                 BlockBox blockBox,
                                                 UserAgentCallback userAgentCallback,
                                                 int cssWidth,
                                                 int cssHeight) {
        Element element = blockBox.getElement();
        if (element == null) {
            return null;
        }
        String nodeName = element.getNodeName();
        if ("img".equals(nodeName)) {
            if (!element.hasAttribute("src")) {
                log.error("Image {} without source", nodeName);
                throw new PdfRenderException("Image has missing a `src` attribute.");
            }
            try {
                String imgSrc = element.getAttribute("src");
                if (imgSrc.isEmpty()) {
                    return null;
                }
                final byte[] bytes = resourceService.getImage(imgSrc);
                final Image image = Image.getInstance(bytes);
                final FSImage fsImage = new ITextFSImage(image);
                if ((cssWidth != -1) || (cssHeight != -1)) {
                    fsImage.scale(cssWidth, cssHeight);
                }
                return new ITextImageElement(fsImage);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new PdfRenderException(String.format("There was a problem with the handling of the image: %s", e.getMessage()));
            }
        }
        return this.superFactory.createReplacedElement(layoutContext, blockBox, userAgentCallback, cssWidth, cssHeight);
    }

    @Override
    public void reset() {
        this.superFactory.reset();
    }

    @Override
    public void remove(Element e) {
        this.superFactory.remove(e);
    }

    @Override
    public void setFormSubmissionListener(FormSubmissionListener listener) {
        this.superFactory.setFormSubmissionListener(listener);
    }
}