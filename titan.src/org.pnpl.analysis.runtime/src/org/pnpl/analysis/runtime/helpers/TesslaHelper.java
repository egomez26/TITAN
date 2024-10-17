package org.pnpl.analysis.runtime.helpers;
import static j2html.TagCreator.body;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.link;
import static j2html.TagCreator.script;
import static j2html.TagCreator.title;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/* import scala.Option;
import scala.Tuple3;
import scala.collection.immutable.Map; */

import javax.script.ScriptException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import j2html.tags.ContainerTag;
import j2html.tags.specialized.HtmlTag;

import de.uni_luebeck.isp.tessla.interpreter.Interpreter;
import de.uni_luebeck.isp.tessla.interpreter.JavaApi;
import de.uni_luebeck.isp.tessla.interpreter.JavaApi.Engine;
import de.uni_luebeck.isp.tessla.interpreter.JavaApi.EngineListener;

import org.pnpl.solvers.cpntools.handlers.Event;

/*class TesslaEvent {
    String color;
    Number time;
    Number value;
}*/

class TesslaStream {
    String color;
    Vector<Event> data;
    Boolean editable;
    String name;
    String style; // "signal" | "dots" | "graph" | "slim graph" | "plot" | "slim plot" | "events" | "unit events" | "bubbles"
    Object text; // ((e: TesslaEvent) => string) | null;
}


/*
export interface VisualizerOptions {
  axis: boolean;
  axisOffset: number;
  bubbleHeight: number;
  boolHeight: number;
  color: string | ((e: TesslaEvent | null) => string);
  draggerRadius: number;
  dragPrecision: number;
  eventHeight: number;
  fontSize: string;
  labelWidth: number | undefined;
  labelDistance: number;
  maxZoom: number;
  onDrag: (stream: TesslaStream, dragIndex: number) => void | undefined;
  onDragEnd: (stream: TesslaStream, dragIndex: number) => void | undefined;
  plotHeight: number;
  signalHeight: number;
  streams: TesslaStream[];
  text: ((e: TesslaEvent) => string);
  timeDomain: [number, number] | undefined;
}

export interface Main {
  container: d3.Selection<Element, {}, any, undefined>;
  display: () => void;
  fillColor: (d: TesslaEvent | null) => string;
  labelWidth: number;
  options: VisualizerOptions;
  prefix: string;
  strokeColor: (d: TesslaEvent | null) => string;
  textColor: (d: TesslaEvent | null) => string;
  textLabel: (d: TesslaEvent) => string;
  tooltip: (elements: d3.Selection<d3.BaseType, TesslaEvent, any, {}>) => void;
  tooltipDiv: d3.Selection<d3.BaseType, {}, HTMLElement, any>;
  width: number;
  xScale: d3.ScaleLinear<number, number>;
}

export interface Visualizer {
  disableBrush: () => void;
  display: () => void;
  enableBrush: () => void;
  options: VisualizerOptions;
  resize: () => void;
  showArrows: () => void;
}
*/

public class TesslaHelper {
	private static final String ts_extension = ".tessla";

	private Engine ts_eng = null;
	private Interpreter in = null;
	private String spec = null;
	private String tsfile = "spec.tessla"; /* null, dummy value */
	
	private IFile ts_file = null;

    EngineListener eng_listener = null;

    Hashtable<String, Vector<Event>> outStreams = null;

    private String lib = "./lib";
    private String d3 = "d3.js";
    private String tessla_visualizer = "tessla-visualizer.js";
    public TesslaHelper() {}
	
	public Engine getTesslaEngine() {
		/*if (this.ts_eng == null) {
			this.generateTesslaEngine(spec);
		}*/
		return this.ts_eng;
	}

	public Interpreter getTesslaInterpreter() {
		if (this.in == null) {
			this.in = this.getTesslaEngine().spec();
		}
		return this.in;
	}
	
	public String getTesslaSpec() {
		return this.spec;
	}

	public IFile getTesslaSpecFile() {
		return this.ts_file;
	}
	
	public boolean load(IFile ts_file) {
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(ts_file.getContents(true), ts_file.getCharset()));) {
			/* String line = reader.readLine();
			while (line != null) {
				line += reader.readLine();			
			}
			spec = line;
			
			return loadTesslaSpec(((IFile) ts_file).getProject(),pnfile); */
			
			this.spec = reader.lines().toString();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;		
	}

	public void setTesslaSpec(String spec_str) {
		this.spec = spec_str;
	}

	//	public void loadTesslaFile(IFile tesslaFile) {
	//	this.tesslaFile = tesslaFile;
	//	this.ts = new TesslaHelper();
	//	this.ts.load(tesslaFile);
	//}

	private boolean loadTesslaSpec(IProject project, String tsfile){
		/* IFile tesslaFile
		this.tesslaFile = tesslaFile; 
		ts = new TesslaHelper();
		ts.load(tesslaFile);*/
		File file;

		System.out.println("[pnpl] Loading Tessla spec");

		file = processContainer(project, tsfile, ts_extension);
		if (file != null) {
			IWorkspace workspace= ResourcesPlugin.getWorkspace();
			IPath location= Path.fromOSString(file.getAbsolutePath());
			this.ts_file= workspace.getRoot().getFileForLocation(location);
			this.load(this.ts_file);
		} else {
			return false;
		}

		if (this.spec == null)
			return false;

		return true;
	}

	private File processContainer(IContainer container, String filename, String ext) {
		try {
			IResource[] members = container.members();

			for (IResource res : members) {
				if (res instanceof IContainer) {
					processContainer((IContainer)res, filename,ext);
				} else if (res instanceof IFile) {
					if (res.getName().endsWith(ext)) {
						if (res.getName().equals(filename)) {
							return res.getLocation().toFile();
						}
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

    private URI getFilePathFromResource(String fileName) throws URISyntaxException, MalformedURLException, IOException{
    	ClassLoader cl = getClass().getClassLoader();
    	/* File f = new File(cl.getResource(fileName).getFile()); */
        URL resource = cl.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());
        	URI res = resource.toURI();
        	return FileLocator.toFileURL(res.toURL()).toURI();
        }

    }


	public Engine generateTesslaEngine(String spec_str) {
		System.out.println("Compiling...");
		
		this.setTesslaSpec(spec_str);

		ts_eng = JavaApi.compile(this.spec, this.tsfile).engine();

		System.out.println("Ready!");

        outStreams = new Hashtable<String, Vector<Event>>();
		eng_listener = new EngineListener() {
			public void event(String stream, scala.math.BigInt time, Object value) {
				System.out.println("Output: " + stream + " = " + value + " at " + time);
				Event te = new Event();
				te.setStream(stream);
				te.setTime(time.toInt());
				te.setValue(value); /* Do we support data types beyond Numbers? If not, then cast (Number) value */
				Vector<Event> t_stream = outStreams.get(stream);
				if (t_stream == null) {
					t_stream = new Vector<Event>();
				}
				System.out.println("outStream: " + t_stream.toString());
				t_stream.add(te);
				outStreams.put(stream, t_stream);
			}

			public void printEvent(scala.math.BigInt time, Object value) {

			}
		};

		ts_eng.addListener(this.eng_listener);
		return ts_eng;
	}

	
	
	public void executeTessla(Hashtable<String, Vector<Event>> inputStreams, Engine res) {

		List<Event> tempList = new ArrayList<Event>();

	    Set<String> k = inputStreams.keySet();
	    Iterator<String> it = k.iterator();
	    while (it.hasNext()) {
	    	String streamName = it.next();
	    	Vector<Event> oneStream = inputStreams.get(streamName);
	    	tempList.addAll(oneStream);
	    }

	    // tempList.sort((te1, te2) -> te1.getTime() - te2.getTime());
        Collections.sort(tempList);
	    
		System.out.println("[pnpl] tempList: " + tempList.toString());

	    Iterator<Event> it2 = tempList.iterator();
    	while (it2.hasNext()) {
    		Event te = it2.next();
    		String stream = te.getStream();
    		int timestamp = te.getTime(); 
    		Object value = te.getValue();
    		String type = te.getType();
			res.setTime(timestamp);
			String message = timestamp + ": " + stream;
			if (type.equals("Unit")) {
				/* Tessla stream is Event[Unit] */
				res.provide(stream);
			} else if (type.equals("Int")){
				/* Tessla stream is Event[Int] */
				message = message + " = " + value.toString();
	    		res.provide(stream, ((Integer) value).intValue());
			}
			/* Right now, Titan only supports Integer values (i.e., Event[Unit] or Event[Int]) because of they represent the number of tokens in one place.
			 * Extending Titan to support colours in tokens (in the sense of CPN Tools) implies to choose the right 'provide' function according to the data type, */
			else {
				/* Tessla stream is Event[Object] */
				message = message + " = " + value.toString();
				res.provide(stream, value);
			}
			System.out.println(message);
    	}

	}
	
    public String Streams2Text() {    
    	String res = "tesslaVisualizer.visualizer(container, {\r\n" + 
    			"        streams: [";
         /*{
            style: "dots",
            name: "hans",
            data: [{time: 5, value: 6}, {time: 10, value: 10}, {time: 17, value: 20}],
          }, */
        Set<String> streamNames = outStreams.keySet();
        for (String name: streamNames) {
        	Vector<Event> value = outStreams.get(name);
        	res = res + "{\nstyle: \"dots\",\nname: \"" + name + "\",\ndata:" + TimeEvents2Text(value) + "},\n";
        }
        
		return res + "] });";
    }
    public String TimeEvents2Text(Vector<Event> value) {
    	// [{time: 5, value: 6}, {time: 10, value: 10}, {time: 17, value: 20}]
    	String res = "[";
    	for (int i = 0; i < value.size(); i++) {
    		String timestamp = Integer.valueOf(value.get(i).getTime()).toString();
    		String val = value.get(i).getValue().toString();
    		res = res + "{time: " + timestamp + ", value: " + val +"},";
    	}
    	res = res + "]";
    	return res;
    }
    
    public HtmlTag toHTML(String title) throws NoSuchMethodException, ScriptException, IOException, URISyntaxException {
    	String string_streams = this.Streams2Text();
    	ContainerTag ct = new ContainerTag("svg");
		ct.withId("container").withStyle("width:800px;");
		HtmlTag out = html(
				    head(
				        title(title),
				        link().withRel("stylesheet").withHref("/css/main.css")
				    ),
				    body(
				         h1(title),
				         //img().withId("container").withStyle("width:800px;"),
				         ct,
				         script().withSrc(d3),
				         script().withSrc(tessla_visualizer),
				         script(string_streams)
				    )
			).withLang("en");
		return out;
	}
    
    public void exportHTML(String exportdir, String htmlTesslaFilename) throws URISyntaxException, IOException, NoSuchMethodException, ScriptException {
    	HtmlTag out = this.toHTML("Report");
		String htmltext = out.renderFormatted();
		System.out.println(htmltext);
		/* String htmlTesslaFilename = file.getParent() + htmlTesslaPath;
		dumpText2File(htmlTesslaFilename, htmltext); */
		dumpText2File(exportdir, htmlTesslaFilename, htmltext);
		

		URI path = getFilePathFromResource(lib + '/' + d3);
		java.nio.file.Path origin = Paths.get(path);
		java.nio.file.Path destination = Paths.get(exportdir, d3);
		Files.copy(origin, destination, StandardCopyOption.REPLACE_EXISTING);
		
		String o = origin.normalize().toAbsolutePath().toString();
		String d = destination.normalize().toAbsolutePath().toString();
		System.out.println("Copying file from " + o + " to " + d);

		path = getFilePathFromResource(lib + '/' + tessla_visualizer);
		origin = Paths.get(path);
		destination = Paths.get(exportdir, tessla_visualizer);
		Files.copy(origin, destination, StandardCopyOption.REPLACE_EXISTING);
		
		o = origin.normalize().toAbsolutePath().toString();
		d = destination.normalize().toAbsolutePath().toString();
		System.out.println("Copying file from " + o + " to " + d);
    }


	private void dumpText2File(String directory, String filename, String text) {
		File outFile = new File(directory, filename);
	    try {
	    	System.out.println("Writing to the file: " + filename);
			outFile.createNewFile();
	    	BufferedOutputStream outFileStream = new BufferedOutputStream(new FileOutputStream(outFile, false));			
	    	outFileStream.write(text.getBytes());
	    	outFileStream.close();
	        System.out.println("Successfully wrote to the file: " + filename);
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}

}