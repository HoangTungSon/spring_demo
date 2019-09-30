package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import source.model.Product;
import source.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ModelAndView getAllProduct() {
        Iterable<Product> productList = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

    @GetMapping("view/{id}")
    public ModelAndView viewProduct(@PathVariable("id") Long id){
        Product product = productService.findById(id);
        return new ModelAndView("/product/view", "product", product);
    }

    @GetMapping("/create-product")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/save-product")
    public ModelAndView saveProduct(@ModelAttribute("product") Product product, BindingResult bindingResult) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage", bindingResult.getAllErrors());
        }
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "successfully creating a new product");
        return modelAndView;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView editForm(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/edit-product")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Successfully updating the product");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView deleteForm(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            return new ModelAndView("Error.404");
        }
    }

    @PostMapping("/delete-product")
    public String removeProduct(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:list";
    }
}
