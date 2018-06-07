<?php
class M_pembayaran extends CI_Model {

	public function __construct(){
		parent::__construct();
		$this->load->database();
	}

	function tampil_data(){
		$sql = "select * from tabel_pembayaran";
		$data = $this->db->query($sql);

		return $data->result();
	}
	


}
?>
