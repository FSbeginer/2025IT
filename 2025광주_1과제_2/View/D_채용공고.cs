using _2025광주_1과제_2.Model;
using _2025광주_1과제_2.MyControl;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제_2.View
{
    public partial class D_채용공고 : _2025광주_1과제_2.Template.BF
    {
        public jobposting jobposting { get; set; }
        public D_채용공고()
        {
            InitializeComponent();
        }

        private void D_채용공고_Load(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                var job = db.jobposting.Find(jobposting.jp_no);
                var list = Hp.user.u_sc.Split(',').Select(int.Parse).ToList();
                if (list.Contains(job.company.c_no))
                {
                    pictureBox1.Image = Properties.Resources.save2;
                }
                else
                {
                    pictureBox1.Image = Properties.Resources.save1;
                }
                label8.Text = job.jp_title;
                label9.Text = job.jobtype.jt_name;
                label10.Text = job.jp_situation == 0 ? "경력" : "신입";
                label11.Text = job.location.l_name;
                label12.Text = job.education.e_name;
                label13.Text = job.jp_sc == 1 ? "회사내규" : "회사외규";
                label14.Text = job.occupation.o_name;
                if (db.applicationemployment.Any(x => x.jp_no == job.jp_no && x.u_no == Hp.user.u_no))
                {
                    button1.Text = "지원 취소";
                }
                else
                {
                    button1.Text = "지원";
                }
                label1.Text = jobposting.company.c_name;
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                db.user.Attach(Hp.user);
                db.jobposting.Attach(jobposting);
                var list = Hp.user.u_sc.Split(',').Select(int.Parse).ToList();
                if (list.Contains(jobposting.company.c_no))
                {
                    list.Remove(jobposting.company.c_no);
                    pictureBox1.Image = Properties.Resources.save1;
                }
                else
                {
                    list.Add(jobposting.company.c_no);
                    pictureBox1.Image = Properties.Resources.save2;
                }
                Hp.user.u_sc = string.Join(",", list);
                db.SaveChanges();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                if (button1.Text == "지원")
                {
                    db.jobposting.Attach(jobposting);
                    if (jobposting.start_date > DateTime.Today)
                    {
                        msgErr("채용 공고를 시작하지 않았습니다.");
                        return;
                    }
                    if (jobposting.jp_situation < Hp.user.u_situation)
                    {
                        msgErr("신입이 경력에 지원 할 수 없습니다.");
                        return;
                    }
                    if (jobposting.e_no > Hp.user.e_no)
                    {
                        msgErr("학력 미달 입니다.");
                        return;
                    }
                    if (jobposting.occupation.o_no != Hp.user.o_no)
                    {
                        msgErr("직종이 다릅니다.");
                        return;
                    }
                    button1.Text = "지원 취소";
                    applicationemployment ap = new applicationemployment();
                    ap.jp_no = jobposting.jp_no;
                    ap.u_no = Hp.user.u_no;
                    db.applicationemployment.Add(ap);
                    db.SaveChanges();
                }
                else
                {
                    var ap = db.applicationemployment.FirstOrDefault(x => x.u_no == Hp.user.u_no && x.jp_no == jobposting.jp_no);
                    db.applicationemployment.Remove(ap);
                    db.SaveChanges();
                }
            }
        }
    }
}
