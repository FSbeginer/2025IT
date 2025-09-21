using _2025광주_1과제_2.Model;
using _2025광주_1과제_2.MyControl;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제_2.View
{
    public partial class H_소개서추가 : _2025광주_1과제_2.Template.BF
    {
        public H_소개서추가()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var title = placeHolder1.Text;
            var subtitle = placeHolder2.Text;
            var txt = placeHolder3.Text;
            if (string.IsNullOrEmpty(title))
            {
                msgErr("제목이 없습니다.");
                return;
            }
            if (string.IsNullOrEmpty(subtitle))
            {
                msgErr("부제목이 없습니다.");
                return ;
            }
            if (string.IsNullOrEmpty(txt))
            {
                msgErr("내용이 없습니다.");
                return;
            }
            using (var db = new Model.placementEntities())
            {
                selfintroduction si =new selfintroduction();
                si.si_title = title;
                si.si_subtitle = subtitle;
                si.si_explan = txt;
                si.o_no = Hp.user.o_no;
                si.u_no = Hp.user.u_no;
                db.selfintroduction.Add(si);
                db.SaveChanges();
                msgInfo("소개서를 저장하였습니다.");
                showPage("메인");

            }
        }

        private void placeHolder1_TextChanged(object sender, EventArgs e)
        {
            if (placeHolder1.Text.Length > 30)
            {
                placeHolder1.Text = placeHolder1.Text.Substring(0, 30);
            }
        }

        private void placeHolder2_TextChanged(object sender, EventArgs e)
        {
            if (placeHolder2.Text.Length > 40)
            {
                placeHolder2.Text = placeHolder1.Text.Substring(0, 40);
            }
        }

        private void placeHolder3_TextChanged(object sender, EventArgs e)
        {
            if (placeHolder1.Text.Length > 400)
            {
                placeHolder1.Text = placeHolder1.Text.Substring(0, 400);
            }
        }
    }
}
