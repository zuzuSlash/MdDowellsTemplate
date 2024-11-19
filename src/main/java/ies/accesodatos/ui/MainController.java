package ies.accesodatos.ui;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import ies.accesodatos.DataBaseConnection;
import ies.accesodatos.categorias.dao.CategoriaDao;
import ies.accesodatos.categorias.repository.CategoriaQueryRepository;
import ies.accesodatos.categorias.repository.CategoriaRepository;
import ies.accesodatos.categorias.services.CategoriaCommandService;
import ies.accesodatos.categorias.services.CategoriaQueryService;
import ies.accesodatos.commons.services.Event;

import ies.accesodatos.empleados.DAO.EmpleadoDAO;
import ies.accesodatos.empleados.repository.EmpleadoQueryRepository;
import ies.accesodatos.empleados.services.EmpleadoCommandService;
import ies.accesodatos.empleados.services.EmpleadoQueryService;

import ies.accesodatos.empleados.repository.EmpleadoRepository;
import ies.accesodatos.productos.services.ProductoCommandService;
import ies.accesodatos.productos.services.ProductoQueryService;
import ies.accesodatos.ui.categorias.view.FormularioCategoriaView;
import ies.accesodatos.ui.categorias.view.ListadoCategoriasView;
import ies.accesodatos.ui.categorias.viewmodel.CategoriaViewModel;
import ies.accesodatos.ui.commons.Router;
import ies.accesodatos.ui.empleados.view.FormularioEmpleadoView;
import ies.accesodatos.ui.empleados.view.ListadoEmpleadosView;
import ies.accesodatos.ui.empleados.viewmodel.EmpleadoViewModel;
import ies.accesodatos.ui.productos.view.FormularioProductoView;
import ies.accesodatos.ui.productos.view.ListadoProductosView;
import ies.accesodatos.ui.productos.viewmodel.ProductoViewModel;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import io.github.palexdev.materialfx.utils.ToggleButtonsUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final Stage stage;
    @FXML
    private TextFlow log;
    private double xOffset;
    private double yOffset;
    private final ToggleGroup toggleGroup;
    private Router router;

    private EventBus eventBus;

    private EmpleadoRepository empleadoRepository;
    private EmpleadoQueryRepository empleadoQueryRepository;
    private EmpleadoQueryService service;

    private CategoriaRepository categoriaRepository;
    private CategoriaQueryRepository categoriaQueryRepository;
    private CategoriaQueryService categoriaService;

    //dao's
    private EmpleadoDAO empleadoDAO;
    private CategoriaDao categoriaDao;
    //servicios
    private CategoriaCommandService categoriaCommandService;
    private CategoriaQueryService categoriaQueryService;
    private ProductoCommandService productoCommandService;
    private ProductoQueryService productoQueryService;
    private EmpleadoCommandService empleadoCommandService;
    private EmpleadoQueryService empleadoQueryService;

    private DataBaseConnection connection;
    //view models
    private CategoriaViewModel categoriaViewModel;
    private EmpleadoViewModel empleadoViewModel;
    private ProductoViewModel productoViewModel;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private MFXScrollPane scrollPane;

    @FXML
    private VBox navBar;

    @FXML
    private StackPane contentPane;

    @FXML
    private StackPane logoContainer;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("Iniciando");
        try {
            this.initCommons();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.initRepository();
        this.initServices();
        this.initViewModels();
        this.initCategorias();
        this.initEmpleados();
        this.initProductos();
        ScrollUtils.addSmoothScrolling(scrollPane);


        Image image = new Image(String.valueOf(getClass().getResource("/logo.png")), 32, 32, true, true);
        ImageView logo = new ImageView(image);
        Circle clip = new Circle(30);
        clip.centerXProperty().bind(logo.layoutBoundsProperty().map(Bounds::getCenterX));
        clip.centerYProperty().bind(logo.layoutBoundsProperty().map(Bounds::getCenterY));
        logo.setClip(clip);
        logoContainer.getChildren().add(logo);





    }
    private void initDao (){
        empleadoDAO = new EmpleadoDAO();
        empleadoDAO.setConnection(this.connection);
        categoriaDao = new CategoriaDao();
        categoriaDao.setConnection(this.connection);
    }

    private void initRepository(){
        initDao();
        this.empleadoQueryRepository = new EmpleadoQueryRepository(empleadoDAO);
        this.empleadoRepository = new EmpleadoRepository(empleadoDAO);
        this.categoriaQueryRepository = new CategoriaQueryRepository(categoriaDao);
        this.categoriaRepository = new CategoriaRepository(categoriaDao);
    }

    private void initCommons() throws Exception {
        this.eventBus = new EventBus();
        this.router = new Router();
        this.router.setMain(contentPane);
        this.connection = new DataBaseConnection();
        connection.setConfig_path("/src/main/resources/config.properties");
        connection.open();
    }



    private void initServices(){
        //this.empleadoRepository = new EmpleadoRepository();
      //  this.empleadoQueryRepository = new EmpleadoQueryRepository();

        this.categoriaCommandService = new CategoriaCommandService(categoriaRepository);
        this.categoriaCommandService.register(this);

        this.categoriaQueryService = new CategoriaQueryService(categoriaQueryRepository);

        this.productoCommandService = new ProductoCommandService();
        this.productoCommandService.register(this);
        this.productoQueryService = new ProductoQueryService();

        this.empleadoCommandService = new EmpleadoCommandService(empleadoRepository);
        this.empleadoCommandService.register(this);

        this.empleadoQueryService = new EmpleadoQueryService(empleadoQueryRepository);


    }
    private void initViewModels(){
        this.categoriaViewModel= new CategoriaViewModel();
        this.categoriaViewModel.setCommandService(this.categoriaCommandService);
        this.categoriaViewModel.setQueryService(this.categoriaQueryService);
        this.categoriaViewModel.setProductoCommandService(this.productoCommandService);
        this.empleadoViewModel= new EmpleadoViewModel();
        this.empleadoViewModel.setCommandService(this.empleadoCommandService);
        this.empleadoViewModel.setQueryService(this.empleadoQueryService);

        this.productoViewModel= new ProductoViewModel();
        this.productoViewModel.setCategoriaQueryService(this.categoriaQueryService);
        this.productoViewModel.setCategoriaCommandService(this.categoriaCommandService);
        this.productoViewModel.setCommandService(this.productoCommandService);
        this.productoViewModel.setQueryService(this.productoQueryService);
        //this.categoriaViewModel.load();


    }
    private void initCategorias() {

        //se carga el de las categorias
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/categorias/listado.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        ListadoCategoriasView lcv = loader.getController();
        //se le pasa el router, el viewmodel y se añade al enrutador
        lcv.setRouter(this.router);
        lcv.setViewModel(this.categoriaViewModel);
        this.router.add("categorias", loader);
        //configurar el menuitem
        ToggleButton toggle = createToggle("fas-list", "Categorias");
        //se cambia el elemento
        toggle.setOnAction(event -> {

            router.push("categorias");
            toggle.setSelected(true);

        });
        //añadir
        navBar.getChildren().add(toggle);

        //para la edición
        loader = new FXMLLoader(getClass().getResource("/fxml/categorias/formulario.fxml"));

        this.router.add("categoria", loader);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        FormularioCategoriaView fcv = loader.getController();
        fcv.setViewModel(this.categoriaViewModel);
        fcv.setRouter(this.router);

    }

    private void initProductos() {

        //se carga el de las categorias
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/productos/listado.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        ListadoProductosView lcv = loader.getController();
        //se le pasa el router, el viewmodel y se añade al enrutador
        lcv.setRouter(this.router);
        lcv.setViewModel(this.productoViewModel);
        lcv.setCategoriaViewModel(this.categoriaViewModel);
        this.router.add("productos", loader);
        //configurar el menuitem
        ToggleButton toggle = createToggle("fas-list", "Productos");
        //se cambia el elemento
        toggle.setOnAction(event -> {
            this.categoriaViewModel.unSelect();
            router.push("productos");
            toggle.setSelected(true);

        });
        //añadir
        navBar.getChildren().add(toggle);

        //para la edición
        loader = new FXMLLoader(getClass().getResource("/fxml/productos/formulario.fxml"));

        this.router.add("producto", loader);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        FormularioProductoView fcv = loader.getController();
        fcv.setViewModel(this.productoViewModel);
        fcv.setRouter(this.router);

    }


    private void initEmpleados() {

        //se carga el de las categorias
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/empleados/listado.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        ListadoEmpleadosView lcv = loader.getController();
        //se le pasa el router, el viewmodel y se añade al enrutador
        lcv.setRouter(this.router);
        lcv.setViewModel(this.empleadoViewModel);
        this.router.add("empleados", loader);
        //configurar el menuitem
        ToggleButton toggle = createToggle("fas-list", "Empleados");
        //se cambia el elemento
        toggle.setOnAction(event -> {
            router.push("empleados");
            toggle.setSelected(true);

        });
        //añadir
        navBar.getChildren().add(toggle);

        //para la edición
        loader = new FXMLLoader(getClass().getResource("/fxml/empleados/formulario.fxml"));

        this.router.add("empleado", loader);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        FormularioEmpleadoView fcv = loader.getController();
        fcv.setViewModel(this.empleadoViewModel);
        fcv.setRouter(this.router);

    }



    public MainController(Stage stage) {
        this.stage = stage;
        this.toggleGroup = new ToggleGroup();
        ToggleButtonsUtil.addAlwaysOneSelectedSupport(toggleGroup);
    }

    private ToggleButton createToggle(String icon, String text) {
        return createToggle(icon, text, 0);
    }

    private ToggleButton createToggle(String icon, String text, double rotate) {
        MFXIconWrapper wrapper = new MFXIconWrapper(icon, 24, 32);
        MFXRectangleToggleNode toggleNode = new MFXRectangleToggleNode(text, wrapper);
        toggleNode.setAlignment(Pos.CENTER_LEFT);
        toggleNode.setMaxWidth(Double.MAX_VALUE);
        toggleNode.setToggleGroup(toggleGroup);
        if (rotate != 0) wrapper.getIcon().setRotate(rotate);
        return toggleNode;
    }
    @Subscribe
    public void onEvent(Event event) {
        Text t= new  Text(event.getMessage()+":"+event.getData().toString()+"\n");
        t.setFont(Font.font("Courier New", 12));
        switch (event.getAction()){
            case ADD -> {
                t.setFill(Color.BLUE);
                break;
            }
            case DELETE -> {
                t.setFill(Color.RED);
                break;
            }
            case UPDATE -> {
                t.setFill(Color.GREEN);
                break;
            }

        }
       log.getChildren().add(t);





    }
}
