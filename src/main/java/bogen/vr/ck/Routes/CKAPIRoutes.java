package bogen.vr.ck.Routes;

import bogen.vr.ck.Model.Content;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static bogen.vr.ck.Utility.Statics.JSON_OK;
import static bogen.vr.ck.Utility.Statics.contents;

@RestController
@RequestMapping(path = "/api/ck")
@Validated
public class CKAPIRoutes {

    @PostMapping(value = "store/{contentId}")
    @ResponseBody
    public String store(@PathVariable String contentId, final @RequestBody String data) {

        if(contents.containsKey(contentId))
            contents.get(contentId).setContent(data);
        else contents.put(contentId, new Content(data));

        return JSON_OK;
    }

}
