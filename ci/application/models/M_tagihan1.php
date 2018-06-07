<?php
class M_tagihan1 extends CI_Model {

	public function __construct(){
		parent::__construct();
		$this->load->database();
	}

	function tampil_data(){
		$sql = "SELECT tabel_pembayaran.id_pembayaran, tabel_pembayaran.tanggal_bayar,tabel_pembayaran.jatuh_tempo, tabel_pembayaran.status, tabel_pembayaran.foto, tabel_user.nama_user, tabel_user.alamat, tabel_user.tarif, tabel_user.tanggal_daftar, tabel_user.telp FROM tabel_pembayaran INNER JOIN tabel_user ON tabel_user.id_user=tabel_pembayaran.id_user WHERE tabel_pembayaran.status = 0";
		$data = $this->db->query($sql);

		return $data->result();
	}
	
     function up_status($table, $where, $id, $data){
        
        $this->db->where($where,$id);
		$this->db->update($table,$data);
    }

}
?>
