<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Beranda extends CI_Controller {

	public function __construct (){
		parent::__construct();
		$this->load->model('M_pelanggan');		
	}

	public function index()
	{
		$this->load->view('template/header');
		$this->load->view('template/content');
		$this->load->view('template/footer');
	}
}
