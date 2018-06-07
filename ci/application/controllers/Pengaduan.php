<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Pengaduan extends CI_Controller {

	public function __construct (){
		parent::__construct();
		$this->load->model('M_pengaduan');		
	}

	public function index()
	{
		$this->load->view('template/header');
		$this->load->view('template/content');
		$this->load->view('template/footer');
	}

	public function tampil_tabel_pengaduan()
	{
		$data['jj'] = $this->M_pengaduan->tampil_data();
		$this->load->view('template/header');
		$this->load->view('V_pengaduan',$data);
		$this->load->view('template/footer');
	}
}