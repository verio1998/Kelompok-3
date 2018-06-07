<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Pelanggan extends CI_Controller {

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

	public function tampil_tabel_pelanggan()
	{
		$data['jj'] = $this->M_pelanggan->tampil_data();
		$this->load->view('template/header');
		$this->load->view('V_pelanggan',$data);
		$this->load->view('template/footer');
	}
}