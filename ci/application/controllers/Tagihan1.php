<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Tagihan1 extends CI_Controller {

	public function __construct (){
		parent::__construct();
		$this->load->model('M_tagihan1');		
	}

	public function index()
	{
		$this->load->view('template/header');
		$this->load->view('template/content');
		$this->load->view('template/footer');
	}

	public function tampil_tabel_tagihan1()
	{
		$data['jj'] = $this->M_tagihan1->tampil_data();
		$this->load->view('template/header');
		$this->load->view('V_tagihan1',$data);
		$this->load->view('template/footer');
	}
    
    public function update_status($id){
        $data = array(				
			'status' => "1"
            //'jatuh_tempo => +30',			
		);
        $this->M_tagihan1->up_status('tabel_pembayaran','id_pembayaran', $id, $data);
        redirect('Tagihan1/tampil_tabel_tagihan1');
    }
}