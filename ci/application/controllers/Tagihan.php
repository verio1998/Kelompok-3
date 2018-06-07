<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Tagihan extends CI_Controller {

	public function __construct (){
		parent::__construct();
		$this->load->model('M_tagihan');		
	}

	public function index()
	{
		$this->load->view('template/header');
		$this->load->view('template/content');
		$this->load->view('template/footer');
	}

	public function tampil_tabel_tagihan()
	{
		$data['jj'] = $this->M_tagihan->tampil_data();
		$this->load->view('template/header');
		$this->load->view('V_tagihan',$data);
		$this->load->view('template/footer');
	}
}