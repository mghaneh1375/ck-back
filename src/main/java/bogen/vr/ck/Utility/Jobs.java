package bogen.vr.ck.Utility;

import bogen.vr.ck.CkApplication;
import bogen.vr.ck.Model.Content;

import java.sql.PreparedStatement;
import java.util.*;

import static bogen.vr.ck.Utility.Statics.contents;

public class Jobs implements Runnable {

    @Override
    public void run() {

        Timer timer = new Timer();
        timer.schedule(new SavaInDB(), 0, 1000 * 60 * 5);

    }

    class SavaInDB extends TimerTask {

        @Override
        public void run() {

            long curr = System.currentTimeMillis();

            for(Iterator<Map.Entry<String, Content>> it = contents.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, Content> entry = it.next();
                if(curr - entry.getValue().getLastUpdateAt() > 60000) {
                    try {
                        String sql = "insert into contents (`conetent_id`, `content`) values (?, ?)";
                        PreparedStatement ps = CkApplication.con.prepareStatement(sql);
                        ps.setString(1, entry.getKey());
                        ps.setString(2, entry.getValue().getContent());
                        ps.executeQuery();
                    } catch (Exception x) {
                        x.printStackTrace();
                    }

                    it.remove();
                }
            }

        }

    }
}
