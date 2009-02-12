package net.haefelingerit.flaka;

import java.io.File;
import java.io.InputStream;

import org.apache.tools.ant.BuildException;

/**
 * Task to export a certain file. The file in question is getting retrieved by
 * looking up the search class path. By default the result will be printed on
 * stdout. 
 * 
 */

public class Export extends Task
{
  private String  dst = null;
  private boolean tee = false;
  private String  src = "antlib.xml";

  private InputStream asStream(String fname) {
    if (fname == null)
      return null;
    /* returns null if fname can't be found. */
    return this.getClass().getResourceAsStream(fname);
  }

  private String asString(String fname) {
    return Static.readlines(asStream(fname));
  }

  public void setDst(String s) {
    this.dst = s;
  }

  public void setDest(String s) {
    this.dst = s;
  }

  public void setOut(String s) {
    this.dst = s;
  }

  public void setSrc(String s) {
    this.src = s;
  }

  public void setTee(boolean b) {
    this.tee = b;
  }

  public void execute() throws BuildException {
    String fname, s;
    File F;

    /* read it in one go .. */
    s = asString(this.src);

    if (s == null) {
      info("file \"" + this.src + "\" not found in classpath.");
      return;
    }

    if (this.dst == null || this.dst.equals("-")) {
      /* dump file on stdout */
      log(s);
      /* we are done */
      return;
    }

    fname = this.dst;
    F = new File(fname);

    if (F.isDirectory()) {
      File B = new File(this.src);
      fname = new File(this.dst, B.getName()).getAbsolutePath();
    }
    try {
      if (this.tee) {
        log(s);
      }
      Static.writex(fname, s, false);
      log("\tfile \"" + fname + "\" contains exported resource.");

    }
    catch (Exception e) {
      throwbx("errors while exporting resource to file \"" + fname + "\":", e);
      return;
    }
  }
}
