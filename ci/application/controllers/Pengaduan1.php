<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Pengaduan1 extends CI_Controller {

	public function __construct (){
		parent::__construct();
		$this->load->model('M_pengaduan1');		
	}

	public function index()
	{
		$this->load->view('template/header');
		$this->load->view('template/content');
		$this->load->view('template/footer');
	}

	public function tampil_tabel_pengaduan1()
	{
		$data['jj'] = $this->M_pengaduan1->tampil_data();
		$this->load->view('template/header');
		$this->load->view('V_pengaduan1',$data);
		$this->load->view('template/footer');
	}
    
    public function update_status($id){
        $data = array(				
			'status' => "1",			
		);
        $this->M_pengaduan1->up_status('tabel_pengaduan','id_pengaduan', $id, $data);
        redirect('Pengaduan1/tampil_tabel_pengaduan1');
    }
}