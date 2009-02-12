package net.haefelingerit.flaka;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/**
 * Create a new property by selecting a couple of existing ones ..
 * 
 * @author <a href="mailto:flaka (at) haefelingerit (dot) net">Wolfgang H&auml;felinger</a>
 */

public class Select extends Task
{
  protected String regex    = null;
  protected String property = null;
  protected String sep      = ",";

  public void setProperty(String s) {
    this.property = Static.trim2(s, this.property);
  }

  public void setRegex(String s) {
    this.regex = Static.trim2(s, this.regex);
  }

  public void setSep(String s) {
    this.sep = s;
  }

  public static String[] select_properties(Project P, String regex) {
    String[] L = null;
    try {
      L = Static.grep(P, regex);
    }
    catch (Exception e) {
      Static.verbose(P, "error grepping properties using pattern `" + regex
          + "'", e);
      L = null;
    }
    return L;
  }

  public void execute() throws BuildException {
    Project P;
    String s;
    String[] L;

    if (this.property == null) {
      debug("missing attribute `property` ..");
      return;
    }

    if (this.regex == null) {
      debug("missing attribute `regex' ..");
      return;
    }

    P = project();

    if (P.getProperty(this.property) != null) {
      debug("property `" + this.property + "' already defined (task skipped)");
    }

    /* grep properties */
    L = select_properties(P, this.regex);

    if (L == null) {
      /* propably syntax error in pattern */
      return;
    }

    if (L.length <= 0) {
      verbose("no properties matching pattern `" + this.regex + "', property `"
          + this.property + "' will be empty.");
    }

    s = null;
    for (int i = 0; i < L.length; ++i) {
      String[] S = null;
      try {
        /* split not more than one time */
        S = L[i].split("=", 2);
      }
      catch (Exception ex) {
        continue;
      }
      if (S == null || S.length != 2) {
        continue;
      }

      if (s == null)
        s = S[1];
      else {
        s += this.sep + S[1];
      }
    }
    if (s == null)
      s = "";

    /* will only set if not already set */
    Static.addProperty(P, this.property, s);
  }
}
