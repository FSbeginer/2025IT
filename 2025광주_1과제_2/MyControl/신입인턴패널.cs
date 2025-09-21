using _2025광주_1과제_2.Model;
using _2025광주_1과제_2.Template;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제_2.MyControl
{
    public partial class 신입인턴패널 : UserControl
    {
        public jobposting job { get; set; }
        public 신입인턴패널()
        {
            InitializeComponent();
        }

        private void 신입인턴패널_Load(object sender, EventArgs e)
        {
            label1.Text = job.company.c_name;
            label2.Text = job.jp_title;
            label3.Text = $"{job.location.l_name}  {job.location.l_name} | {(job.jp_sc == 1 ? "회사내규" : "회사외규")} | {(job.jp_situation==1?"신입":"경력")} | {job.jobtype.jt_name} | {job.education.e_name}";
            var daydiff = job.start_date.Date - DateTime.Now.Date;
            label4.Text = Math.Abs(daydiff.Days) + "일 " + (daydiff.Days < 0 ? "전" : "후");
            var list = Hp.user.u_sc.Split(',').Select(int.Parse).ToList();
            if (list.Contains(job.company.c_no))
            {
                pictureBox1.Image = Properties.Resources.save2;
            }
            else
            {
                pictureBox1.Image = Properties.Resources.save1;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            ((BF)FindForm()).showPage(new View.D_채용공고() { jobposting = job});
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                db.user.Attach(Hp.user);

                var list = Hp.user.u_sc.Split(',').Select(int.Parse).ToList();
                if (list.Contains(job.company.c_no))
                {
                    list.Remove(job.company.c_no);
                    pictureBox1.Image = Properties.Resources.save1;
                }
                else
                {
                    list.Add(job.company.c_no);
                    pictureBox1.Image = Properties.Resources.save2;
                }
                Hp.user.u_sc = string.Join(",", list);
                db.SaveChanges();
            }
        }
    }
}
