using _2025광주_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제.View
{
    public partial class Form소개서추가 : _2025광주_1과제.Model.BF
    {
        public Form소개서추가()
        {
            InitializeComponent();
        }

        private void Form소개서추가_Load(object sender, EventArgs e)
        {
            placeHolder2.textBox1.Multiline = true;
            placeHolder1.Msg = "제목을 입력해주세요.";
            placeHolder2.Msg = "제목을 입력해주세요.";
            placeHolder3.Msg = "제목을 입력해주세요.";
            
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(placeHolder1.textBox1.Text))
            {
                Hp.msgErr("제목이 없습니다.");
                return;
            }
            if (string.IsNullOrWhiteSpace(placeHolder3.textBox1.Text))
            {
                Hp.msgErr("부제목이 없습니다.");
                return;
            }
            if (string.IsNullOrWhiteSpace(placeHolder2.textBox1.Text))
            {
                Hp.msgErr("내용이 없습니다.");
                return;
            }
            using (var db =new Model.placementEntities())
            {
                var sel = new selfintroduction();
                sel.u_no = Hp.user.u_no;
                sel.si_title = placeHolder1.textBox1.Text;
                sel.si_subtitle = placeHolder3.textBox1.Text;
                sel.si_explan = placeHolder2.textBox1.Text;
                sel.o_no = Hp.user.o_no;
                db.selfintroduction.Add(sel);
                db.SaveChanges();
            }
            Hp.msgInfo("소개서를 저장하였습니다.");
            showPage("메인");
        }
    }
}
