using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025경기_1과제.View
{
    public partial class Sale : _2025경기_1과제.Template.BF
    {
        public Sale()
        {
            InitializeComponent();
        }

        private void Sale_Load(object sender, EventArgs e)
        {
            using (var db = new Model.SkillCinemaEntities())
            {
                int cnt = Hp.user.seatcount;
                textBox1.Text = cnt / 10+"";
                textBox2.Text = cnt >= 5 && cnt <= 10 ? "1" : "0";
                textBox2.ForeColor= cnt>=5&&cnt<=10?Color.Black:Color.Silver;

            }
        }
    }
}
