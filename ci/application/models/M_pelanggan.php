<?php
class M_pelanggan extends CI_Model {

	public function __construct(){
		parent::__construct();
		$this->load->database();
	}

	function tampil_data(){
		$sql = "select * from tabel_user";
		$data = $this->db->query($sql);

		return $data->result();
	}
	


}
?>
