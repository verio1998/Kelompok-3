<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>NO</th>
                                        <th style="display:none;">ID</th>
                                        <th>Nama</th>                            
                                        <th>Alamat</th>
                                        <th>Telp</th>
                                        <th>Email</th>
                                        <th>Tarif</th>
                                        <th>Tanggal Pendaftaran</th>
                                    </tr>
                                </thead>
                                <tbody>
<?php 
        $no = 1;
        foreach($jj as $u){ 
        ?>
                  <tr>
                  <td><?php echo $no++ ?></td>
                  <td style="display:none;"><?php echo $u->id_user ?></td>
                  <td><?php echo $u->nama_user ?></td>
                  <td><?php echo $u->alamat ?></td>
                  <td><?php echo $u->telp ?></td>
                  <td><?php echo $u->email ?></td>
                  <td><?php echo $u->tarif ?></td>
                  <td><?php echo $u->tanggal_daftar ?></td>
                  </tr>     

                                     <?php } ?>
                                </tbody>
                            
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->