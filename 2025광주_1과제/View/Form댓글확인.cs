using _2025광주_1과제.Controls;
using _2025광주_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제.View
{
    public partial class Form댓글확인 : _2025광주_1과제.Model.BF
    {
        List<Control소개서내용> controls = new List<Control소개서내용>(); 
        List<selfintroduction> datas = new List<selfintroduction>();
        public Form댓글확인()
        {
            InitializeComponent();
        }

        private void Form댓글확인_Load(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                comboBox1.DataSource = db.selfintroduction.Where(x=>x.u_no == Hp.user.u_no).Select(x=>x.si_title).ToList();
                datas = db.selfintroduction.Where(x => x.u_no == Hp.user.u_no).ToList();
            }
            panel1.MouseWheel += Panel1_MouseWheel;
            getdata();
        }

        private void Panel1_MouseWheel(object sender, MouseEventArgs e)
        {
            int dx = e.Delta > 0 ? 10 : -10;
            if (controls.Count == 0 || controls[0].Top + dx > 0 || controls.Last().Bottom + dx < panel1.Height-10) return;
            foreach (var item in controls)
            {
                item.Top += dx;
            }
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            getdata();
        }

        public void getdata()
        {
            controls.Clear();
            panel1.Controls.Clear();
            using (var db = new Model.placementEntities())
            {
                var list = db.comment.AsEnumerable().Where(x=>datas[comboBox1.SelectedIndex].si_no == x.si_no).ToList();
                foreach (var item in list)
                {
                    Control소개서내용 d = new Control소개서내용 { comment = item, delete = true };
                    d.Location = new Point(0, (d.Height + 10) * controls.Count);
                    
                    panel1.Controls.Add(d);
                    controls.Add(d);
                }
                label1.Text = controls.Count + " 개";
            }
        }
    }
}
