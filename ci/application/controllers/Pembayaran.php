<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Pembayaran extends CI_Controller {

	public function __construct (){
		parent::__construct();
		$this->load->model('M_pembayaran');		
	}

	public function index()
	{
		$this->load->view('template/header');
		$this->load->view('template/content');
		$this->load->view('template/footer');
	}

	public function tampil_tabel_pembayaran()
	{
		$data['jj'] = $this->M_pembayaran->tampil_data();
		$this->load->view('template/header');
		$this->load->view('V_pembayaran',$data);
		$this->load->view('template/footer');
	}
}