package bogen.vr.ck.Routes;

import bogen.vr.ck.CkApplication;
import bogen.vr.ck.Model.Content;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static bogen.vr.ck.Utility.Statics.*;

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

    @GetMapping(value = "get/{contentId}")
    @ResponseBody
    public String get(@PathVariable Object contentId) {

        if(contents.containsKey(contentId.toString()))
            return generateSuccessMsg("data", contents.get(contentId.toString()).getContent());

        try {
            String sql = "select content from contents where content_id = ?";
            PreparedStatement ps = CkApplication.con.prepareStatement(sql);
            ps.setString(1, contentId.toString());
            ResultSet rs = ps.executeQuery();

            if(rs.next())
                return generateSuccessMsg("data", rs.getString(1));

        }
        catch (Exception x) {
            x.printStackTrace();
        }

        return generateSuccessMsg("data", "");
    }

}
