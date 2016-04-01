package br.com.fpc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fpc.entities.Values;
import br.com.fpc.repositories.ModelRepository;
import br.com.fpc.repositories.ValuesRepository;
import br.com.fpc.utils.Types;

/**
 * @author fernando.costa
 *
 * Classe responsável por gerenciar as requisições do usuário
 */
@Controller
public class ModelController {

	@Autowired
	private ModelRepository mr = new ModelRepository();
	
	@Autowired
	private ValuesRepository vsr = new ValuesRepository();

	/**
	 * Mapeia as requisições para a página Home
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/", "/home" })
	public String modelo(@RequestParam(required=false) String msg, Model model) {
		model.addAttribute("msg", msg);
		return "home";
	}

	/**
	 * Mapeia a requisição para a página de criação de modelos
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/model", method = RequestMethod.GET)
	public String modelForm(Model model) {
		model.addAttribute("model", new br.com.fpc.entities.Model());
		model.addAttribute("types", Types.values());
		return "model";
	}

	/**
	 * 
	 * Mapeia a requisição para a criação de um modelo
	 * 
	 * @param req
	 * @param m
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/model", method = RequestMethod.POST)
	public String modelSubmit(br.com.fpc.entities.Model m, Model model) {
		String msg = "Erro ao criar modelo!";
		if(mr.create(m) > 0){
			msg = "Sucesso ao criar modelo!";
		}
		model.addAttribute("msg", msg);
		return "home";
	}

	/**
	 * 
	 * Mapeia a requisição para a página de listagem de modelos
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/models", method = RequestMethod.GET)
	public String modelsList(Model model, @RequestParam(required=false) String msg) {
		model.addAttribute("msg", msg);
		model.addAttribute("models", mr.findAll());
		return "models";
	}

	/**
	 * 
	 * Mapeia a requisição para a página de criação de um modelo específico
	 * 
	 * @param req
	 * @param modelUrl
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{modelUrl}/create", method = RequestMethod.GET)
	public String createModel(@PathVariable String modelUrl, Model model) {
		model.addAttribute("model", mr.findByUrl(modelUrl));
		return "create";
	}

	/**
	 * 
	 * Mapeia a requisição para a página de listagem de todos os cadastros de um determinado modelo
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{modelUrl}", method = RequestMethod.GET)
	public String listModel(@PathVariable String modelUrl, Model model) {
		br.com.fpc.entities.Model m = mr.findByUrl(modelUrl);
		model.addAttribute("model", m);
		
		List<Values> vs = vsr.findByModel(m);
		model.addAttribute("values", vs);
		
		return "list";
	}

	/**
	 * 
	 * Mapeia a requisição para retornar um objeto JSON que representa um cadastro específico de um modelo
	 * 
	 * @param req
	 * @param modelId
	 * @param model
	 * @return json
	 */
	@RequestMapping(value = "/{modelUrl}/{valueId:[\\d]+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Values listModels(@PathVariable long valueId) {
		return vsr.find(valueId);
	}

	/**
	 * 
	 * Mapeia a requisição para a criação de um cadastro de um modelo específico
	 * 
	 * @param req
	 * @param values
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{modelUrl}", method = RequestMethod.POST)
	public String createModel(Values values, Model model, RedirectAttributes redirectAttributes) {
		String msg = "Erro ao criar modelo!";
		if(vsr.add(values) > 0){
			msg = "Sucesso ao criar modelo!";
		}
		redirectAttributes.addAttribute("msg", msg);
		return "redirect:/models";
	}

	/**
	 * 
	 * Mapeia a requisição para a deleção de um modelo
	 * 
	 * @param req
	 * @param modelId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/model/{modelId:[\\d]+}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteModel(@PathVariable long modelId, Model model) {
		mr.remove(mr.find(modelId));
		return "sucesso";
	}
	
	
	/**
	 * 
	 * Mapeia a requisição para a página de edição de um cadastro de um modelo específico
	 * 
	 * @param req
	 * @param modelId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{modelUrl}/{valueId:[\\d]+}/edit", method = RequestMethod.GET)
	public String listModel(@PathVariable String modelUrl, @PathVariable long valueId, Model model) {
		Values values =  vsr.find(valueId);

		model.addAttribute("values", values);
		model.addAttribute("model", mr.findByUrl(modelUrl));
		
		return "edit";
	}

	/**
	 * 
	 * Mapeia a requisição para a atualização de um cadastro de um modelo específico
	 * 
	 * @param req
	 * @param modelId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{modelUrl}/{modelId:[\\d]+}", method = RequestMethod.PUT)
	public String editModel(Values values, Model model) {
		model.addAttribute("msg", "Produto atualizado com sucesso!");
		vsr.update(values);
		return "home";
	}
	
	/**
	 * 
	 * Mapeia a requisição para a deleção de um cadastro de um modelo específico
	 * 
	 * @param req
	 * @param modelId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{modelUrl}/{valueId:[\\d]+}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteValue(@PathVariable long valueId, Model model) {
		vsr.remove(vsr.find(valueId));
		return "sucesso";
	}
}
