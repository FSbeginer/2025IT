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
    public partial class Form소개서내용 : _2025광주_1과제.Model.BF
    {
        public Model.selfintroduction selfintroduction { get; set; }
        List<Control소개서내용> controls = new List<Control소개서내용>();
        public Form소개서내용()
        {
            InitializeComponent();
        }

        private void Form소개서내용_Load(object sender, EventArgs e)
        {
            var list =Hp.user.u_si.Split(',').Select(int.Parse).ToList();
            if (list.Contains(selfintroduction.si_no))
                pictureBox1.Image = Properties.Resources.save2;
            else
                pictureBox1.Image = Properties.Resources.save1;

            label1.Text = "제목 : " + selfintroduction.si_title;
            label2.Text = "부제목 : "+selfintroduction.si_subtitle;
            label4.Text = selfintroduction.si_explan;

            load();

        }

        private void load()
        {
            panel4.Controls.Clear();
            controls.Clear();
            using (var db = new Model.placementEntities())
            {
                var comments = db.comment.Where(x => x.si_no == selfintroduction.si_no).ToList();
                foreach (var comment in comments)
                {
                    Control소개서내용 c = new Control소개서내용 { comment = comment };
                    c.Location = new Point(10, 10+(c.Height +10)*controls.Count);
                    panel4.Controls.Add(c);
                    controls.Add(c);
                }
                panel4.MouseWheel += Panel4_MouseWheel;

            }
        }

        private void Panel4_MouseWheel(object sender, MouseEventArgs e)
        {
            int dy = e.Delta > 0 ? 10 : -10;
            if (controls.Count==0|| controls[0].Top + dy > 0 || controls.Last().Bottom + dy < panel4.Height - 20 ) return;
            foreach (var item in controls)
            {
                item.Top += dy;
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                db.user.Attach(Hp.user);

                List<int> ints = Hp.user.u_si.Split(',').Select(int.Parse).ToList();
                if (ints.Contains(selfintroduction.si_no))
                {
                    ints.Remove(selfintroduction.si_no);
                    Hp.user.u_si = string.Join(",", ints);
                    pictureBox1.Image = Properties.Resources.save1;
                }
                else
                {
                    ints.Add(selfintroduction.si_no);
                    Hp.user.u_si = string.Join(",", ints);
                    pictureBox1.Image = Properties.Resources.save2;
                }

                db.SaveChanges();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            using (var db= new Model.placementEntities())
            {
                var cm = new comment();
                cm.u_no = Hp.user.u_no;
                cm.si_no = selfintroduction.si_no;
                cm.cm_date = DateTime.Now.Date;
                cm.cm_explan = textBox1.Text;
                db.comment.Add(cm);
                db.SaveChanges();
                textBox1.Text = "";
                load();
            }
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            if(textBox1.Text.Length > 100)
                textBox1.Text = textBox1.Text.Substring(0, 100);
        }
    }
}
