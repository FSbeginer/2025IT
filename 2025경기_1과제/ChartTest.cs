using _2025경기_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025경기_1과제
{
    public partial class ChartTest : Form
    {
        public ChartTest()
        {
            InitializeComponent();
        }

        private void ChartTest_Load(object sender, EventArgs e)
        {
            List<int> movies = new List<int> { 1, 2, 3, 5, 6, 7 };

            //차트 시리즈 초기화
            this.chart1.Series.Clear();
            for (int i = 0; i < movies.Count; i++)
            {
                this.chart1.Series.Add("s" + i);
                this.chart1.Series[i].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Line;
                this.dataGridView1.Columns.Add("c" + i, (i + 1) + "일차");
            }


            using (var db = new Model.SkillCinemaEntities())
            {
                var reservations = db.reservation.Where(x => movies.Contains(x.mno)).GroupBy(x => x.mno).ToList();

                for (global::System.Int32 i = 0; i < reservations.Count; i++)
                {
                    var result = reservations[i].GroupBy(x => x.rdate.Date).Select(x => x.Count()).ToList();
                    this.chart1.Series[i].Points.DataBindY(result);
                }
            }

            this.dataGridView1.Rows.Add("asdf", 1, 1, 1, 1, 1, 1, 1);
        }
    }
}
