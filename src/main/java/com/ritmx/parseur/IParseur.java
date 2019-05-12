package com.ritmx.parseur;

import com.ritmx.model.RitmxConnexion;

import java.io.File;
import java.util.List;

/** design Pattern strategy
 * We put an interface in case another use case ask for different type of file (.txt, .xml, .json)
 */
public interface IParseur {

    List<RitmxConnexion> parse(File file);
}
