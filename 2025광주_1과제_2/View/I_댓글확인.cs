using _2025광주_1과제_2.Model;
using _2025광주_1과제_2.MyControl;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제_2.View
{
    public partial class I_댓글확인 : _2025광주_1과제_2.Template.BF
    {
        public I_댓글확인()
        {
            InitializeComponent();
        }

        List<selfintroduction> list = new List<selfintroduction>();
        List<댓글> pps = new List<댓글> { };
        private void I_댓글확인_Load(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                list = db.selfintroduction.Where(x=>Hp.user.u_no==x.u_no).ToList();
                
                comboBox1.DataSource = list.Select(x=>x.si_title).ToList();
            }
            panel1.MouseWheel += Panel1_MouseWheel;
            panelLoad();
          
        }

        public void panelLoad()
        {
            panel1.Controls.Clear();
            pps.Clear();
            using (var db = new Model.placementEntities())
            {
                var cms = db.comment.AsEnumerable().Where(x => x.si_no == list[comboBox1.SelectedIndex].si_no).ToList();
                foreach (var item in cms)
                {
                    댓글 com = new 댓글() { comment = item };
                    com.Location = new Point(0, (com.Height + 10) * pps.Count);
                    panel1.Controls.Add(com);
                    pps.Add(com);
                }
                label1.Text = cms.Count + " 개";
            }
        }

        private void Panel1_MouseWheel(object sender, MouseEventArgs e)
        {
            int dx = e.Delta < 0 ? -10 : 10;
            if (pps.Count == 0 || pps[0].Top + dx > 0 || pps.Last().Bottom + dx < panel1.Height) return;
            foreach (var item in pps)
            {
                item.Top += dx;
            }
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            panelLoad();
        }
    }
}
